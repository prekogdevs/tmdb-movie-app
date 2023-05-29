package com.tmdb.movieapp.data.remote.mapper

import com.tmdb.movieapp.AppConstants
import com.tmdb.movieapp.data.remote.response.MovieDetailsResponse
import com.tmdb.movieapp.domain.DetailedMovie
import com.tmdb.movieapp.domain.SimplifiedMovie

fun MovieDetailsResponse.toSimplifiedMovie() = SimplifiedMovie(
    id = id,
    name = original_title,
    budget = budget,
    overview = overview,
    poster_path = poster_path?.toPosterURL()
)

fun MovieDetailsResponse.toDetailedMovie() = DetailedMovie(
    id = id,
    originalTitle = original_title,
    originalLanguage = original_language,
    releaseDate = release_date,
    budget = budget,
    revenue = revenue,
    tagline = tagline,
    overview = overview,
    posterURL = poster_path?.toPosterURL()
)

fun String?.toPosterURL() = if (this == null) null else AppConstants.IMAGE_PATH + this
