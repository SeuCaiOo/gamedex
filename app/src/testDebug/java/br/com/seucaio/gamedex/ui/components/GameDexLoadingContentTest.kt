package br.com.seucaio.gamedex.ui.components

import android.app.Application
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import br.com.seucaio.gamedex.ui.theme.GameDexTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = Application::class)
class GameDexLoadingContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsCircularProgressIndicator() {
        composeTestRule.setContent {
            GameDexTheme(dynamicColor = false) {
                GameDexLoadingContent()
            }
        }

        composeTestRule.onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertExists()
    }
}
