package com.tmdb.movieapp

import com.google.common.truth.Truth.assertThat
import com.tmdb.movieapp.data.remote.response.MovieDetailsResponse
import com.tmdb.movieapp.data.remote.response.MovieListResponse
import com.tmdb.movieapp.data.remote.response.MovieSearchResult
import com.tmdb.movieapp.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class MovieRepositoryTest {

    private lateinit var repository: MovieRepository
    private val fakeApiKey = "fakeApiKey"
    private val fakeQuery = "fakeQuery"
    private val fakePageNumber = 1

    @Before
    fun setup() {
        repository = mockk()
    }

    @Test
    fun `test getMovies`() {
        val expected = MovieListResponse(
            page = 1,
            results = listOf(
                MovieSearchResult(
                    id = 1,
                    original_title = "Fight Club",
                    overview = "Good movie",
                    poster_path = null
                ),
                MovieSearchResult(
                    id = 2,
                    original_title = "Rambo",
                    overview = "Good action movie",
                    poster_path = null
                )
            )
        )
        var actual: MovieListResponse

        coEvery {
            repository.getMovies(
                apiKey = any(),
                query = any(),
                page = any()
            )
        } returns expected

        runBlocking {
            actual = repository.getMovies(
                apiKey = fakeApiKey,
                query = fakeQuery,
                page = fakePageNumber
            )
        }

        assertThat(actual).isEqualTo(expected)

        coVerify {
            repository.getMovies(
                apiKey = any(),
                query = any(),
                page = any()
            )
        }
    }

    @Test
    fun `test getMovieById`() {
        val expected = MovieDetailsResponse(
            id = 1,
            original_title = "Fight Club",
            original_language = "en",
            budget = 100000,
            release_date = "2000-01-27",
            overview = "Good movie",
            revenue = 300000,
            tagline = "",
            poster_path = null
        )
        var actual: MovieDetailsResponse

        coEvery {
            repository.getMovieById(
                movieId = any()
            )
        } returns expected

        runBlocking {
            actual = repository.getMovieById(
                movieId = 1
            )
        }

        assertThat(actual).isEqualTo(expected)

        coVerify {
            repository.getMovieById(
                movieId = any()
            )
        }
    }
}