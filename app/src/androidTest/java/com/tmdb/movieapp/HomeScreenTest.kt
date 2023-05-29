package com.tmdb.movieapp

import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.tmdb.movieapp.presentation.MainActivity
import com.tmdb.movieapp.presentation.navigation.AppNavigation
import com.tmdb.movieapp.presentation.scaffold.AppScaffold
import com.tmdb.movieapp.ui.theme.TMDBMovieAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            TMDBMovieAppTheme {
                AppScaffold(navController = navController, backStackEntry = backStackEntry) {
                    AppNavigation(navController = navController, paddingValues = it)
                }
            }
        }
    }

    @Test
    fun componentsOnHomeScreenAreDisplayed() {
        composeRule.onNodeWithTag(ComponentTags.SEARCH_FIELD).assertExists().assertIsDisplayed()
        composeRule.onNodeWithTag(ComponentTags.SEARCH_RESULT_PLACEHOLDER).assertExists()
            .assertIsDisplayed()
        composeRule.onNodeWithTag(ComponentTags.HOME_SCREEN_TOP_BAR).assertExists()
            .assertIsDisplayed()
    }
}