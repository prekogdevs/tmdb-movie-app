package com.tmdb.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tmdb.movieapp.presentation.navigation.AppNavigation
import com.tmdb.movieapp.presentation.scaffold.AppScaffold
import com.tmdb.movieapp.ui.theme.TMDBMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBMovieAppTheme(darkTheme = true) {
                val navController = rememberNavController()
                val backStackEntry by navController.currentBackStackEntryAsState()
                AppScaffold(
                    navController = navController,
                    backStackEntry = backStackEntry
                ) { paddingValues ->
                    AppNavigation(
                        navController = navController,
                        paddingValues = paddingValues
                    )
                }
            }
        }
    }
}

