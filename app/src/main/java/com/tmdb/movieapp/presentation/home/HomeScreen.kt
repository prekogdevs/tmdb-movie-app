package com.tmdb.movieapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.tmdb.movieapp.ComponentTags
import com.tmdb.movieapp.R
import com.tmdb.movieapp.ui.theme.primaryColor

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onMovieItemClick: (Int) -> Unit
) {
    val query by homeViewModel.query.collectAsState()
    val movies = homeViewModel.moviePager.collectAsLazyPagingItems()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
    ) {
        Divider(color = primaryColor, thickness = 1.dp)
        SearchField(
            modifier = Modifier.fillMaxWidth(),
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
                Text(
                    modifier = Modifier.testTag(ComponentTags.SEARCH_RESULT_PLACEHOLDER),
                    text = stringResource(R.string.txtSearchResultsLabel),
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            when (movies.loadState.refresh) {
                is LoadState.Loading -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is LoadState.NotLoading -> {
                    LazyColumn(
                        modifier = Modifier.testTag(ComponentTags.SEARCH_RESULTS),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        items(movies.itemCount) { index ->
                            val movie = movies[index]
                            movie?.let {
                                MovieItem(
                                    modifier = Modifier.fillMaxWidth(),
                                    simplifiedMovie = movie,
                                    onMovieItemClick = {
                                        onMovieItemClick(movie.id)
                                    }
                                )
                            }
                        }
                    }
                }

                is LoadState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.txtError),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}