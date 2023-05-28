package com.tmdb.movieapp.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmdb.movieapp.R
import com.tmdb.movieapp.presentation.MoviePoster

@Composable
// TODO: Add contentDescription
fun DetailsScreen(
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        MoviePoster(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(bottom = 8.dp),
            imageUrl = null // TODO
        )
        Row {
            Text(text = stringResource(R.string.txtMovieTagline))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(text = stringResource(R.string.txtMovieTitle))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(text = stringResource(R.string.txtMovieLanguage))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(text = stringResource(R.string.txtMovieReleaseDate))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = stringResource(R.string.txtMovieOverview)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = stringResource(R.string.txtMovieBudget)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(text = stringResource(R.string.txtMovieRevenue))
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}