package com.tmdb.movieapp

import com.google.common.truth.Truth.assertThat
import com.tmdb.movieapp.domain.repository.MovieRepository
import com.tmdb.movieapp.presentation.home.HomeViewModel
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel
    private val mockMovieRepository = mockk<MovieRepository>()

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(movieRepository = mockMovieRepository)
    }

    @Test
    fun `test onQueryChanged updates query value`() {
        homeViewModel.onQueryChanged(query = "query")
        assertThat(homeViewModel.query.value).isEqualTo("query")
    }

}