package com.tmdb.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import com.tmdb.movieapp.presentation.home.HomeScreen
import com.tmdb.movieapp.ui.theme.TMDBMovieAppTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBMovieAppTheme(darkTheme = false) {
                Scaffold(
                    content = { paddingValues ->
                        HomeScreen(paddingValues = paddingValues)
                    }
                )
            }
        }
    }
}

