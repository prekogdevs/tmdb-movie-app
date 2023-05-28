package com.tmdb.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import com.tmdb.movieapp.presentation.navigation.Navigation
import com.tmdb.movieapp.ui.theme.TMDBMovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMDBMovieAppTheme(darkTheme = false) {
                Scaffold(
                    topBar = {
                        // TODO:  Add topBar
                    },
                    content = { paddingValues ->
                        Navigation(paddingValues = paddingValues)
                    }
                )
            }
        }
    }
}

