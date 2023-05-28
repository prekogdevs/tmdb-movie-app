package com.tmdb.movieapp.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.movieapp.domain.DetailedMovie
import com.tmdb.movieapp.data.remote.mapper.toDetailedMovie
import com.tmdb.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _movie = MutableStateFlow(DetailedMovie())
    val movie = _movie.asStateFlow()

    init {
        val movieId = savedStateHandle.get<Int>("movie_id")
        if (movieId != null) {
            getMovieById(movieId = movieId)
        }
    }

    private fun getMovieById(movieId: Int) = viewModelScope.launch {
        val response = movieRepository.getMovieById(movieId = movieId)
        _movie.value = response.toDetailedMovie()
    }
}