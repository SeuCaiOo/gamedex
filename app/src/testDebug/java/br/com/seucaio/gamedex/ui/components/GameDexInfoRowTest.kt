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
class GameDexInfoRowTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupContent(label: String, value: String? = null) {
        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexInfoRow(label = label, value = value)
            }
        }
    }

    @Test
    fun whenValueProvided_shouldShowLabelAndValue() {
        val label = "Release Date"
        val value = "Oct 26, 2023"

        setupContent(label = label, value = value)

        composeTestRule.onNodeWithText(label).assertIsDisplayed()
        composeTestRule.onNodeWithText(value).assertIsDisplayed()
    }

    @Test
    fun whenValueIsNull_shouldRenderNothing() {
        val label = "ShouldNotAppear"

        setupContent(label = label)

        composeTestRule.onNodeWithText(label).assertDoesNotExist()
    }
}
