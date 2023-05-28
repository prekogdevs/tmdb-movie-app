package com.tmdb.movieapp.presentation.scaffold

import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TitledTopBar(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    navigationIcon: @Composable (() -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        title = title,
        navigationIcon = navigationIcon
    )
}