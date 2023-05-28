package com.tmdb.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.tmdb.movieapp.data.MovieDataSource
import com.tmdb.movieapp.domain.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private var movieDataSource: MovieDataSource? = null
        get() {
            if (field == null || field?.invalid == true) {
                field = MovieDataSource(movieRepository = movieRepository, query = query.value)
            }
            return field
        }
    val moviePager = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { movieDataSource!! }
    ).flow.debounce(500).cachedIn(viewModelScope)

    fun onQueryChanged(query: String) {
        _query.value = query
        movieDataSource?.invalidate()
    }
}