package com.tmb.tmdbmovieapp

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
    val composeTestRule = createComposeRule()

    @Test
    fun searchFieldExists() {
        composeTestRule.setContent {
            SearchField(value = "", onValueChange = {})
        }
        composeTestRule.onNodeWithTag("SearchField").assertExists().assertIsDisplayed()
    }

    @Test
    fun searchFieldValueChangeTest() {
        var query by mutableStateOf("")
        composeTestRule.setContent {
            SearchField(value = query, onValueChange = {
                query = it
            })
        }
        composeTestRule.onNodeWithTag("SearchField").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithTag("SearchField").performTextInput("query")
        composeTestRule.onNodeWithTag("SearchField").assertExists().assertIsDisplayed()
            .assertTextEquals("query")
    }
}