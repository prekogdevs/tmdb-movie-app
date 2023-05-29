package com.tmdb.movieapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import com.tmdb.movieapp.presentation.home.SearchField
import org.junit.Rule
import org.junit.Test

class SearchFieldTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun searchFieldExists() {
        composeRule.setContent {
            SearchField(value = "", onValueChange = {})
        }
        composeRule.onNodeWithTag(ComponentTags.SEARCH_FIELD).assertExists().assertIsDisplayed()
    }

    @Test
    fun searchFieldValueChangeTest() {
        var query by mutableStateOf("")
        composeRule.setContent {
            SearchField(value = query, onValueChange = {
                query = it
            })
        }
        composeRule.onNodeWithTag(ComponentTags.SEARCH_FIELD).assertExists().assertIsDisplayed()
        composeRule.onNodeWithTag(ComponentTags.SEARCH_FIELD).performTextInput("query")
        composeRule.onNodeWithTag(ComponentTags.SEARCH_FIELD).assertExists().assertIsDisplayed()
            .assertTextEquals("query")
    }
}