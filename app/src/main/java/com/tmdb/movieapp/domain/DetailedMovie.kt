package com.tmdb.movieapp.domain

data class DetailedMovie(
    val id: Int = -1,
    val originalTitle: String = "",
    val originalLanguage: String = "",
    val releaseDate: String = "",
    val budget: Int = -1,
    val revenue: Int = -1,
    val tagline: String = "",
    val overview: String = "",
    val posterURL: String? = null
)