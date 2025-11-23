package br.com.seucaio.gamedex.ui.components

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import br.com.seucaio.gamedex.ui.theme.GameDexTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class)
class GameDexDescriptionInfoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupContent(description: String) {
        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexDescriptionInfo(description = description)
            }
        }
    }

    @Test
    fun whenDescriptionIsProvided_shouldDisplayTheDescription() {
        val description = "This is a test description."

        setupContent(description)

        composeTestRule.onNodeWithText(description).assertIsDisplayed()
        composeTestRule.onNodeWithText("About").assertIsDisplayed()
    }

    @Test
    fun whenDescriptionIsBlank_shouldDisplayNoDescriptionMessage() {
        val expectedDescription = "No description"

        setupContent(" ")

        composeTestRule.onNodeWithText(expectedDescription).assertIsDisplayed()
        composeTestRule.onNodeWithText("About").assertIsDisplayed()
    }
}
