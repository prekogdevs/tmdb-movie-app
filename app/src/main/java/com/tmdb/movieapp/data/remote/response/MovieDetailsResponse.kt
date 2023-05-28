package com.tmdb.movieapp.data.remote.response

data class MovieDetailsResponse(
    val id: Int,
    val original_title: String,
    val original_language: String,
    val release_date: String,
    val budget: Int,
    val revenue: Int = 0,
    val tagline: String = "",
    val overview: String,
    val poster_path: String?
)