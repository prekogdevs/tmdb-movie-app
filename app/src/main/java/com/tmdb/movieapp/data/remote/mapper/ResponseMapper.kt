package com.tmdb.movieapp.data.remote.mapper

import com.tmdb.movieapp.AppConstants
import com.tmdb.movieapp.data.SimplifiedMovie
import com.tmdb.movieapp.data.local.MovieSearchResult

fun MovieSearchResult.toSimplifiedMovie() = SimplifiedMovie(
    id = id,
    name = original_title,
    budget = 0, // TODO
    overview = overview,
    poster_path = poster_path?.toPosterURL()
)

fun String?.toPosterURL() = AppConstants.IMAGE_PATH + this
