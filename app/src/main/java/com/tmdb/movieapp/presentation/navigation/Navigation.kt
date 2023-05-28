package com.tmdb.movieapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tmdb.movieapp.presentation.details.DetailsScreen
import com.tmdb.movieapp.presentation.home.HomeScreen

@Composable
fun Navigation(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route) {
            HomeScreen(
                paddingValues = paddingValues,
                onMovieItemClick = { movieId ->
                    navController.navigate(Screen.DetailsScreen.route + "/$movieId")
                }
            )
        }
        composable(
            route = Screen.DetailsScreen.route + "/{movie_id}",
            arguments = listOf(
                navArgument(name = "movie_id") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) {
            val movieId = it.arguments?.getInt("movie_id") ?: -1
            DetailsScreen(movieId = movieId)
        }
    }
}