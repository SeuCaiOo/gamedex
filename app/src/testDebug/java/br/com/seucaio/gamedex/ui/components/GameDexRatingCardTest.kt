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
class GameDexRatingCardTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysLabelAndScore() {
        val label = "Metacritic"
        val score = "88"

        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexRatingCard(label = label, score = score)
            }
        }

        composeTestRule.onNodeWithText(label).assertIsDisplayed()
        composeTestRule.onNodeWithText(score).assertIsDisplayed()
    }
}
