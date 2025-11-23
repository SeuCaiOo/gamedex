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
class GameDexInfoListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysName_andInvokesClick() {
        val name = "Grand Theft Auto"
        var clicked = false

        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexInfoListItem(name = name, onItemClick = { clicked = true })
            }
        }

        composeTestRule.onNodeWithText(name, substring = false).assertIsDisplayed()
        composeTestRule.onNode(hasClickAction()).performClick()
        assertTrue(clicked)
    }
}
