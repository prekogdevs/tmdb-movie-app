package com.tmdb.movieapp.data.remote.response

data class MovieListResponse(
    val page: Int,
    val results: List<MovieSearchResult>
)