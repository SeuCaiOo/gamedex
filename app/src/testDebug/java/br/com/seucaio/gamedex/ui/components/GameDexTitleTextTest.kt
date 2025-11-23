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
class GameDexTitleTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysTitle() {
        val title = "Top Games"

        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexTitleText(title = title)
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }
}
