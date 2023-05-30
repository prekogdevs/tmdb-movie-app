package com.tmdb.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.tmdb.movieapp.data.MovieDataSource
import com.tmdb.movieapp.data.remote.mapper.toSimplifiedMovie
import com.tmdb.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val queryDelay = 500L // 500ms
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private var movieDataSource: MovieDataSource? = null
        get() {
            if (field == null || field?.invalid == true) {
                field = MovieDataSource(
                    movieRepository = movieRepository,
                    query = query.value,
                    initialPage = 1
                )
            }
            return field
        }

    val moviePager = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { movieDataSource!! }
    ).flow
        .debounce(queryDelay)
        .map { pagingData ->
            pagingData.map { movieSearchResult ->
                val movie = movieRepository.getMovieById(movieId = movieSearchResult.id)
                movie.toSimplifiedMovie()
            }
        }
        .cachedIn(viewModelScope)

    fun onQueryChanged(query: String) {
        _query.update { query }
        movieDataSource?.invalidate()
    }
}