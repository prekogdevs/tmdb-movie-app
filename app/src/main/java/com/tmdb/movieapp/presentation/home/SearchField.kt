package com.tmdb.movieapp.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.tmdb.movieapp.ComponentTags
import com.tmdb.movieapp.R

@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        modifier = modifier.testTag(ComponentTags.SEARCH_FIELD),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        placeholder = { Text(text = stringResource(R.string.txtSearchQueryPlaceholder)) },
        trailingIcon = {
            if (value.isNotEmpty()) {
                Icon(
                    modifier = Modifier.clickable {
                        onValueChange("")
                    },
                    imageVector = Icons.Default.Clear,
                    contentDescription = stringResource(R.string.txtClearQuery)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.txtSearch)
                )
            }
        })
}