package com.tmdb.movieapp.data.local

data class MovieDetailsResponse(
    val id: Int,
    val original_title: String,
    val original_language: String,
    val release_date: String,
    val budget: Int,
    val revenue: Long = 0,
    val tagline: String = "",
    val overview: String,
    val poster_path: String?
)