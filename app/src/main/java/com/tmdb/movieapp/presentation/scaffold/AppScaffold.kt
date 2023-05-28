package com.tmdb.movieapp.presentation.scaffold

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.tmdb.movieapp.R
import com.tmdb.movieapp.presentation.navigation.Screen

@Composable
fun AppScaffold(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry?,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current
    val title = destinationTitle(context, backStackEntry)
    Scaffold(
        topBar = {
            if (shouldShowBackArrow(backStackEntry = backStackEntry)) {
                TitledTopBar(
                    title = { Text(text = title, color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigateUp()
                        }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                tint = Color.White,
                                contentDescription = title
                            )
                        }

                    }
                )
            } else {
                StandardTopBar(
                    title = { Text(text = title, color = Color.White) },
                )
            }
        },
        content = content
    )
}

private fun destinationTitle(context: Context, backStackEntry: NavBackStackEntry?): String {
    return when (backStackEntry?.destination?.route) {
        Screen.HomeScreen.route -> context.getString(R.string.txtMovieDatabase)
        else -> context.getString(R.string.txtMovieDetails)
    }
}

private fun shouldShowBackArrow(backStackEntry: NavBackStackEntry?): Boolean {
    return backStackEntry?.destination?.route?.contains(Screen.DetailsScreen.route) == true
}

@Composable
fun StandardTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = title
    )
}
