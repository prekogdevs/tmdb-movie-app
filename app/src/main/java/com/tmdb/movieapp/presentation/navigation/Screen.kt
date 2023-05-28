package com.tmdb.movieapp.presentation.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(route = "home_screen")
    object DetailsScreen : Screen(route = "details_screen")
}
