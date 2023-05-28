package com.tmdb.movieapp.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(paddingValues: PaddingValues) {
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
            value = "", // TODO: query
            onValueChange = { query ->
                // TODO: Movie item click
            }
        )
        LazyColumn {
            items(10) {
                MovieItem()
            }
        }
    }
}