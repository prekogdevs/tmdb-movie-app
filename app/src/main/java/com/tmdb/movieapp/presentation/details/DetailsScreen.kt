package com.tmdb.movieapp.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tmdb.movieapp.R
import com.tmdb.movieapp.domain.formatToUSD
import com.tmdb.movieapp.presentation.MoviePoster
import com.tmdb.movieapp.presentation.PartiallyBoldStringBuilder

@Composable
fun DetailsScreen(
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    @Suppress("UNUSED_PARAMETER") movieId: Int
) {
    val detailedMovie by detailsViewModel.movie.collectAsState()

    val budgetText = if (detailedMovie.budget == 0) {
        PartiallyBoldStringBuilder.buildPartiallyBoldString(
            boldPart = stringResource(id = R.string.txtMovieBudget),
            normalPart = stringResource(id = R.string.txtUnknown)
        )
    } else {
        PartiallyBoldStringBuilder.buildPartiallyBoldString(
            boldPart = stringResource(id = R.string.txtMovieBudget),
            normalPart = detailedMovie.budget.formatToUSD()
        )
    }

    val overviewText = PartiallyBoldStringBuilder.buildPartiallyBoldString(
        boldPart = stringResource(id = R.string.txtMovieOverview),
        normalPart = detailedMovie.overview.ifEmpty { stringResource(id = R.string.txtUnknown) }
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        MoviePoster(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(bottom = 8.dp),
            imageUrl = detailedMovie.posterURL
        )
        if (detailedMovie.tagline.isNotEmpty()) {
            Row {
                Text(
                    text = PartiallyBoldStringBuilder.buildPartiallyBoldString(
                        boldPart = stringResource(R.string.txtMovieTagline),
                        normalPart = detailedMovie.tagline
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        Row {
            Text(
                text = PartiallyBoldStringBuilder.buildPartiallyBoldString(
                    boldPart = stringResource(R.string.txtMovieTitle),
                    normalPart = detailedMovie.originalTitle
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = PartiallyBoldStringBuilder.buildPartiallyBoldString(
                    boldPart = stringResource(R.string.txtMovieLanguage),
                    normalPart = detailedMovie.originalLanguage
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = PartiallyBoldStringBuilder.buildPartiallyBoldString(
                    boldPart = stringResource(R.string.txtMovieReleaseDate),
                    normalPart = detailedMovie.releaseDate
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(text = overviewText)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(text = budgetText)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Text(
                text = PartiallyBoldStringBuilder.buildPartiallyBoldString(
                    boldPart = stringResource(R.string.txtMovieRevenue),
                    normalPart = detailedMovie.revenue.formatToUSD()
                )
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}