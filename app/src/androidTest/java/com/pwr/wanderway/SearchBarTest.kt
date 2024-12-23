package com.pwr.wanderway.presentation.routeCore.composable

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class SearchBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchBar_noSuggestionsDisplayedInitially() {
        val suggestions = listOf(
            SearchBarItem("1", "Park", "A beautiful park"),
            SearchBarItem("2", "Museum", "Historical artifacts")
        )

        composeTestRule.setContent {
            SearchBar(
                suggestions = suggestions,
                onSearch = {},
                query = ""
            )
        }

        // Verify the suggestions container is not displayed
        composeTestRule.onNodeWithTag("SuggestionsContainer").assertDoesNotExist()
    }

    @Test
    fun searchBar_displaysSuggestionsWhenTyping() {
        val suggestions = listOf(
            SearchBarItem("1", "Park", "A beautiful park"),
            SearchBarItem("2", "Museum", "Historical artifacts")
        )

        composeTestRule.setContent {
            SearchBar(
                suggestions = suggestions,
                onSearch = {},
                query = ""
            )
        }

        // Type into the search input field
        composeTestRule.onNodeWithTag("SearchInput").performTextInput("Park")

        // Verify suggestions container becomes visible
        composeTestRule.onNodeWithTag("SuggestionsContainer").assertExists()

        // Verify that the correct suggestion is displayed
        composeTestRule.onNodeWithTag("SuggestionItem-1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("SuggestionItem-2").assertDoesNotExist()
    }


    @Test
    fun searchBar_executesOnSearchCallbackWhenItemSelected() {
        val suggestions = listOf(
            SearchBarItem("1", "Park", "A beautiful park"),
            SearchBarItem("2", "Museum", "Historical artifacts")
        )
        var searchedItemId: String? = null

        composeTestRule.setContent {
            SearchBar(
                suggestions = suggestions,
                onSearch = { searchedItemId = it },
                query = ""
            )
        }

        // Type into the search input field
        composeTestRule.onNodeWithTag("SearchInput").performTextInput("Museum")

        // Click on a suggestion
        composeTestRule.onNodeWithTag("SuggestionItem-2").performClick()

        // Verify the callback is executed with the correct ID
        assert(searchedItemId == "2")
    }


    @Test
    fun searchBar_resetsExpandedStateOnSearch() {
        val suggestions = listOf(
            SearchBarItem("1", "Park", "A beautiful park"),
            SearchBarItem("2", "Museum", "Historical artifacts")
        )

        composeTestRule.setContent {
            SearchBar(
                suggestions = suggestions,
                onSearch = {},
                query = ""
            )
        }

        composeTestRule.onNodeWithTag("SearchInput").performTextInput("Museum")

        composeTestRule.onNodeWithTag("SuggestionItem-2").performClick()

        composeTestRule.onNodeWithTag("SuggestionsContainer").assertDoesNotExist()
    }
}
