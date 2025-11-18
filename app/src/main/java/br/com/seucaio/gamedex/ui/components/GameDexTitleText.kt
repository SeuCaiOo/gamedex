package br.com.seucaio.gamedex.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun GameDexTitleText(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold
    )
}

@PreviewLightDark
@Composable
private fun GameDexTitleTextPreview() {
    GameDexTheme {
        Surface {
            GameDexTitleText(title = "Top Games", modifier = Modifier.fillMaxWidth())
        }
    }
}
