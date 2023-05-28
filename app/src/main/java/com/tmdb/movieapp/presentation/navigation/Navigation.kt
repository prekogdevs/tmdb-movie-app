package com.tmdb.movieapp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                onMovieItemClick = {
                    navController.navigate(Screen.DetailsScreen.route)
                }
            )
        }
        composable(route = Screen.DetailsScreen.route) {
            DetailsScreen()
        }
    }
}