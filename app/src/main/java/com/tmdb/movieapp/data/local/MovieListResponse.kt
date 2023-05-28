package com.tmdb.movieapp.data.local

data class MovieListResponse(
    val page: Int,
    val results: List<MovieSearchResult>
)