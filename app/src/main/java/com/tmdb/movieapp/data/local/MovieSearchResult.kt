package com.tmdb.movieapp.data.local

data class MovieSearchResult(
    val id: Int,
    val original_title: String,
    val overview: String,
    val poster_path: String?
)