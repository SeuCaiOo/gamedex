package br.com.seucaio.gamedex.ui.components

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.seucaio.gamedex.ui.theme.GameDexTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class)
class GameDexTopAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val title = "GameDex"
    private val backButtonContentDescription = "Back"


    private fun setupContent(onBackButtonClick: (() -> Unit)? = null) {
        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexTopAppBar(title = title, onBackButtonClick = onBackButtonClick)
            }
        }
    }

    @Test
    fun displaysTitle_andBackButtonInvokesCallback() {
        var backClicked = false

        setupContent(onBackButtonClick = { backClicked = true })

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription(backButtonContentDescription)
            .assertIsDisplayed()
        composeTestRule.onNode(hasClickAction()).performClick()
        assertTrue(backClicked)
    }

    @Test
    fun displaysTitle_withoutBackButton() {
        setupContent()

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription(backButtonContentDescription)
            .assertIsNotDisplayed()
    }
}
