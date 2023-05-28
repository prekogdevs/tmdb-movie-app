package com.tmdb.movieapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tmdb.movieapp.R

// TODO: Placeholder, No image
@Composable
fun MoviePoster(
    modifier: Modifier = Modifier,
    imageUrl: String?
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl ?: R.drawable.ic_launcher_background)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_background),
        contentDescription = stringResource(R.string.txtMoviePoster)
    )
}