package com.tmdb.movieapp.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tmdb.movieapp.R
import com.tmdb.movieapp.data.SimplifiedMovie
import com.tmdb.movieapp.presentation.MoviePoster

@Composable
fun MovieItem(
    simplifiedMovie: SimplifiedMovie,
    onMovieItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable {
                onMovieItemClick()
            }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(1f)) {
                MoviePoster(
                    modifier = Modifier.fillMaxSize(),
                    imageUrl = simplifiedMovie.poster_path
                )
            }
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.txtMovieTitle) + simplifiedMovie.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.txtMovieBudget) + simplifiedMovie.budget,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(id = R.string.txtMovieOverview) + simplifiedMovie.overview,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }
        }
    }
}