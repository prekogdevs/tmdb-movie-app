package com.tmdb.movieapp.data.repository

import com.tmdb.movieapp.data.local.MovieDetailsResponse
import com.tmdb.movieapp.data.local.MovieListResponse
import com.tmdb.movieapp.data.remote.MovieApi
import com.tmdb.movieapp.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {
    override suspend fun getMovies(
        apiKey: String,
        query: String,
        page: Int
    ): MovieListResponse {
        return api.getMovies(apiKey = apiKey, query = query, page = page)
    }

    override suspend fun getMovieById(
        apiKey: String,
        movieId: Int
    ): MovieDetailsResponse {
        return api.getMovieById(apiKey = apiKey, movieId = movieId)
    }

}