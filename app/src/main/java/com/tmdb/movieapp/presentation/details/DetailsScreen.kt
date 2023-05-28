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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmdb.movieapp.R
import com.tmdb.movieapp.presentation.MoviePoster

@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    movieId: Int
) {
    val movie by detailsViewModel.movie.collectAsState()
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
            imageUrl = movie.posterURL
        )
        Row {
            Text(text = stringResource(R.string.txtMovieTagline) + movie.tagline)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = stringResource(R.string.txtMovieTitle) + movie.originalTitle
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = stringResource(R.string.txtMovieLanguage) + movie.originalLanguage
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = stringResource(R.string.txtMovieReleaseDate) + movie.releaseDate
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = stringResource(R.string.txtMovieOverview) + movie.overview
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = stringResource(R.string.txtMovieBudget) + movie.budget + " $"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = stringResource(R.string.txtMovieRevenue) + movie.revenue + " $"
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}