package com.tmdb.movieapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.tmdb.movieapp.R
import com.tmdb.movieapp.domain.SimplifiedMovie
import com.tmdb.movieapp.domain.formatToUSD
import com.tmdb.movieapp.presentation.MoviePoster
import com.tmdb.movieapp.presentation.PartiallyBoldStringBuilder

@Composable
fun MovieItem(
    modifier: Modifier = Modifier,
    simplifiedMovie: SimplifiedMovie,
    onMovieItemClick: (Int) -> Unit
) {
    val budgetText = if (simplifiedMovie.budget == 0) {
        PartiallyBoldStringBuilder.buildPartiallyBoldString(
            boldPart = stringResource(id = R.string.txtMovieBudget),
            normalPart = stringResource(id = R.string.txtUnknown)
        )
    } else {
        PartiallyBoldStringBuilder.buildPartiallyBoldString(
            boldPart = stringResource(id = R.string.txtMovieBudget),
            normalPart = simplifiedMovie.budget.formatToUSD()
        )
    }

    val overviewText = PartiallyBoldStringBuilder.buildPartiallyBoldString(
        boldPart = stringResource(id = R.string.txtMovieOverview),
        normalPart = simplifiedMovie.overview.ifEmpty { stringResource(id = R.string.txtUnknown) }
    )


    Card(
        modifier = modifier.clickable {
            onMovieItemClick(simplifiedMovie.id)
        },
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.DarkGray)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                MoviePoster(imageUrl = simplifiedMovie.poster_path)
            }
            Column(modifier = Modifier.weight(2f)) {
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = PartiallyBoldStringBuilder.buildPartiallyBoldString(
                        boldPart = stringResource(id = R.string.txtMovieTitle),
                        normalPart = " ${simplifiedMovie.name}"
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Text(
                    modifier = Modifier.padding(all = 8.dp),
                    text = budgetText,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = overviewText,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
            }
        }
    }
}