package br.com.seucaio.gamedex.ui.components

import android.app.Application
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
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
class GameDexGridItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setupContent(
        id: Int,
        title: String,
        backgroundImageUrl: String? = null,
        onItemClick: (Int) -> Unit = {}
    ) {
        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexGridItem(
                    id = id,
                    title = title,
                    backgroundImageUrl = backgroundImageUrl,
                    onItemClick = onItemClick
                )
            }
        }
    }

    @Test
    fun displaysTitleWithoutBackgroundImage_andInvokesClickWithId() {
        val id = 42
        val title = "The Witcher"
        var clickedId: Int? = null

        setupContent(
            id = id,
            title = title,
            onItemClick = { clickedId = it }
        )

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithTag("background_image").isNotDisplayed()
        composeTestRule.onNode(hasClickAction()).performClick()
        assertEquals(id, clickedId)
    }

    @Test
    fun displaysTitleWithBackgroundImage_andInvokesClickWithId() {
        val id = 42
        val title = "The Witcher"
        var clickedId: Int? = null

        setupContent(
            id = id,
            title = title,
            backgroundImageUrl = "https://example.com/image.png",
            onItemClick = { clickedId = it }
        )

        composeTestRule.onNodeWithText(title).assertIsDisplayed()
        composeTestRule.onNodeWithTag("background_image").isDisplayed()
        composeTestRule.onNode(hasClickAction()).performClick()
        assertEquals(id, clickedId)
    }
}
