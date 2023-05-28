package com.tmdb.movieapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdb.movieapp.R
import com.tmdb.movieapp.data.remote.mapper.toSimplifiedMovie

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onMovieItemClick: (Int) -> Unit
) {
    val query by homeViewModel.query.collectAsState()
    val movies = homeViewModel.moviePager.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        SearchField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            value = query,
            onValueChange = { query ->
                homeViewModel.onQueryChanged(query = query)
            }
        )
        if (movies.itemCount == 0) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.txtSearchResultsLabel))
            }
        } else {
            LazyColumn {
                items(movies.itemCount) { index ->
                    val movie = movies[index]?.toSimplifiedMovie()
                    movie?.let {
                        MovieItem(
                            simplifiedMovie = movie,
                            onMovieItemClick = {
                                onMovieItemClick(movie.id)
                            }
                        )
                    }
                }
            }
        }
    }
}