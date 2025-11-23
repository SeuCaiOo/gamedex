package br.com.seucaio.gamedex.ui.components

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.seucaio.gamedex.ui.theme.GameDexTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class)
class GameDexListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysTitleAndDescription_andInvokesClickWithId() {
        val id = 7
        val title = "PlayStation 5"
        val description = "120 games"
        var clickedId: Int? = null

        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexListItem(
                    id = id,
                    title = title,
                    description = description,
                    onItemClick = { clickedId = it }
                )
            }
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithText(description).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Show details").assertIsDisplayed()
        composeTestRule.onNode(hasClickAction()).performClick()
        assertEquals(id, clickedId)
    }
}
