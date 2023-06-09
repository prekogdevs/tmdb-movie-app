package com.tmdb.movieapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tmdb.movieapp.R

@Composable
fun MoviePoster(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl ?: R.drawable.movie_without_poster)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.movie_without_poster),
        contentDescription = stringResource(R.string.txtMoviePoster),
        contentScale = ContentScale.Crop
    )
}