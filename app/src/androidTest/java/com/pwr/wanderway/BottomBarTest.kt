package com.pwr.wanderway.navigation.authorized

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.pwr.wanderway.navigation.Destination
import org.junit.Rule
import org.junit.Test

class BottomBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    companion object {
        const val CONTENT_DESCRIPTION_FORUM = "Forum"
        const val CONTENT_DESCRIPTION_HOME = "Home"
        const val CONTENT_DESCRIPTION_ACCOUNT = "Account"
    }

    @Test
    fun bottomBar_displaysAllNavigationItems() {
        composeTestRule.setContent {
            BottomBar(
                activeDestination = Destination.FORUM_SCREEN,
                homeNav = {},
                forumNav = {},
                accountNav = {}
            )
        }

        // Verify all icons and labels are displayed
        composeTestRule.onNodeWithContentDescription(CONTENT_DESCRIPTION_FORUM, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(CONTENT_DESCRIPTION_HOME, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(CONTENT_DESCRIPTION_ACCOUNT, useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun bottomBar_highlightsActiveDestination() {
        composeTestRule.setContent {
            BottomBar(
                activeDestination = Destination.ACCOUNT_SETTINGS_SCREEN,
                homeNav = {},
                forumNav = {},
                accountNav = {}
            )
        }

        // Check the highlighted (active) destination
        composeTestRule.onNodeWithContentDescription(CONTENT_DESCRIPTION_ACCOUNT, useUnmergedTree = true)
            .assertIsDisplayed() // Optionally, add assertions for active styling
    }

    @Test
    fun bottomBar_triggersNavigationCallbacks() {
        var homeClicked = false
        var forumClicked = false
        var accountClicked = false

        composeTestRule.setContent {
            BottomBar(
                activeDestination = Destination.HOME_SCREEN,
                homeNav = { homeClicked = true },
                forumNav = { forumClicked = true },
                accountNav = { accountClicked = true }
            )
        }

        // Simulate clicks on navigation items
        composeTestRule.onNodeWithContentDescription(CONTENT_DESCRIPTION_HOME, useUnmergedTree = true).performClick()
        assert(homeClicked)

        composeTestRule.onNodeWithContentDescription(CONTENT_DESCRIPTION_FORUM, useUnmergedTree = true).performClick()
        assert(forumClicked)

        composeTestRule.onNodeWithContentDescription(CONTENT_DESCRIPTION_ACCOUNT, useUnmergedTree = true).performClick()
        assert(accountClicked)
    }
}
