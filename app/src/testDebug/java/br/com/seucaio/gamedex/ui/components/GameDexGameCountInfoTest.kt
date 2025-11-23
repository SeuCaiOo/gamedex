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
class GameDexGameCountInfoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupContent(count: Int) {
        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexGameCountInfo(count = count)
            }
        }
    }

    @Test
    fun whenCountGreaterThanZero_shouldContainCountNumberInText() {
        val count = 150

        setupContent(count)

        composeTestRule.onNodeWithText("$count games").assertIsDisplayed()
    }

    @Test
    fun whenCountIsZero_shouldNoGamesInText() {
        val count = 0

        setupContent(count)

        composeTestRule.onNodeWithText("No games").assertIsDisplayed()
    }
}
