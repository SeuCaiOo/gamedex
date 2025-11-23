package br.com.seucaio.gamedex.ui.components

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
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
class GameDexErrorContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysCustomTitleAndMessage_andRetryCallbackIsInvoked() {
        val title = "Oops!"
        val message = "Something went wrong"
        var retried = false

        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexErrorContent(
                    title = title,
                    message = message,
                    onRetry = { retried = true }
                )
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(message).assertIsDisplayed()
        composeTestRule.onNodeWithText("Try Again").assertIsDisplayed()

        // Click on the only clickable button (Try again)
        composeTestRule.onNode(hasClickAction()).performClick()
        assertTrue(retried)
    }
}
