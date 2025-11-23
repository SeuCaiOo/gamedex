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
class GameDexRatingInfoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysAllProvidedCards() {
        val infos = listOf(
            "Metacritic" to "90",
            "User Score" to "9.2"
        )

        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexRatingInfo(infos = infos)
            }
        }

        composeTestRule.onNodeWithText("Ratings").assertIsDisplayed()
        infos.forEach { (label, value) ->
            composeTestRule.onNodeWithText(label).assertIsDisplayed()
            composeTestRule.onNodeWithText(value).assertIsDisplayed()
        }
    }
}
