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
class GameDexHeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupContent(title: String, backgroundImageUrl: String? = null) {
        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexHeader(title = title, backgroundImageUrl = backgroundImageUrl)
            }
        }
    }

    @Test
    fun whenNoImage_shouldDisplayTitle() {
        val title = "Game Title"

        setupContent(title = title)

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun whenImageProvided_shouldDisplayTitleOverImage() {
        val title = "Another Game"

        setupContent(title = title, backgroundImageUrl = "https://example.com/image.png")

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }
}
