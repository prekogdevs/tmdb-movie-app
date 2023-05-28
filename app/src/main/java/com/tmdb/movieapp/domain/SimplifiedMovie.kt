package com.tmdb.movieapp.domain

data class SimplifiedMovie(
    val id: Int,
    val name: String,
    val budget: Long,
    val overview: String,
    val poster_path: String?
)