package br.com.seucaio.gamedex.ui.components

import android.app.Application
import androidx.compose.ui.test.assertIsNotDisplayed
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
class GameDexTagInfoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupContent(info: String? = null) {
        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexTagInfo(info = info)
            }
        }
    }

    @Test
    fun whenInfoProvided_shouldDisplayTag() {
        val text = "Action"
        setupContent(info = text)

        composeTestRule.onNodeWithText(text).assertIsDisplayed()
    }

    @Test
    fun whenInfoIsNull_shouldRenderNothing() {
        val absent = "ShouldNotExist"
        setupContent()

        composeTestRule.onNodeWithText(absent).assertDoesNotExist()
    }
}
