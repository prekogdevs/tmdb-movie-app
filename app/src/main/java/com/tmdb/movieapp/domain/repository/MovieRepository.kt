package com.tmdb.movieapp.domain.repository

import com.tmdb.movieapp.AppConstants
import com.tmdb.movieapp.data.remote.response.MovieDetailsResponse
import com.tmdb.movieapp.data.remote.response.MovieListResponse

interface MovieRepository {
    suspend fun getMovies(
        apiKey: String = AppConstants.API_KEY,
        query: String,
        page: Int
    ): MovieListResponse

    suspend fun getMovieById(
        apiKey: String = AppConstants.API_KEY,
        movieId: Int
    ): MovieDetailsResponse
}