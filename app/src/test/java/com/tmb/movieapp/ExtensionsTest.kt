package com.tmb.movieapp

import com.google.common.truth.Truth.assertThat
import com.tmdb.movieapp.AppConstants
import com.tmdb.movieapp.data.remote.mapper.toDetailedMovie
import com.tmdb.movieapp.data.remote.mapper.toPosterURL
import com.tmdb.movieapp.data.remote.mapper.toSimplifiedMovie
import com.tmdb.movieapp.data.remote.response.MovieDetailsResponse
import com.tmdb.movieapp.domain.DetailedMovie
import com.tmdb.movieapp.domain.SimplifiedMovie
import org.junit.Test

class ExtensionsTest {

    @Test
    fun `mapping of MovieDetailsResponse to SimplifiedMovie`() {
        val movieDetailsResponse = MovieDetailsResponse(
            id = 1,
            original_title = "Fight Club",
            original_language = "en",
            release_date = "",
            budget = 0,
            revenue = 0,
            tagline = "",
            overview = "",
            poster_path = null
        )

        val expected = SimplifiedMovie(
            id = 1,
            name = "Fight Club",
            budget = 0,
            overview = "",
            poster_path = null
        )

        val actual = movieDetailsResponse.toSimplifiedMovie()

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `mapping of MovieDetailsResponse to DetailedMove`() {
        val movieDetailsResponse = MovieDetailsResponse(
            id = 1,
            original_title = "Fight Club",
            original_language = "en",
            release_date = "",
            budget = 0,
            revenue = 0,
            tagline = "",
            overview = "",
            poster_path = null
        )

        val expected = DetailedMovie(
            id = 1,
            originalTitle = "Fight Club",
            originalLanguage = "en",
            releaseDate = "",
            budget = 0,
            revenue = 0,
            tagline = "",
            overview = "",
            posterURL = null
        )

        val actual = movieDetailsResponse.toDetailedMovie()

        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `mapping a nullable String to posterURL`() {
        val url: String? = null
        val actual = url.toPosterURL()
        assertThat(actual).isNull()
    }

    @Test
    fun `mapping a String value to posterURL`() {
        val url = "some-image-url"
        val actual = url.toPosterURL()
        val expected = AppConstants.IMAGE_PATH + url
        assertThat(actual).isEqualTo(expected)
    }
}