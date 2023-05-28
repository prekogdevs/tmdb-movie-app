package com.tmdb.movieapp.data.remote.mapper

import com.tmdb.movieapp.AppConstants
import com.tmdb.movieapp.data.DetailedMovie
import com.tmdb.movieapp.data.SimplifiedMovie
import com.tmdb.movieapp.data.local.MovieDetailsResponse
import com.tmdb.movieapp.data.local.MovieSearchResult

fun MovieSearchResult.toSimplifiedMovie() = SimplifiedMovie(
    id = id,
    name = original_title,
    budget = 0, // TODO
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

fun String?.toPosterURL() = AppConstants.IMAGE_PATH + this
