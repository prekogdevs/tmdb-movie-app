package com.tmdb.movieapp.data.remote.response

data class MovieSearchResult(
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String?
)