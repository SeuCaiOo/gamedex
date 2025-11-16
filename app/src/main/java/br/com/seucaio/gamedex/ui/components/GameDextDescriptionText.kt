package br.com.seucaio.gamedex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.model.platform.GamePlatformDetail
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun GameDexDescriptionInfo(
    description: String,
    modifier: Modifier = Modifier,
) {
    val text: String = description.ifBlank { stringResource(R.string.no_description) }
    Text(
        modifier = modifier,
        text = AnnotatedString.fromHtml(htmlString = text.trimIndent()),
        style = MaterialTheme.typography.bodyLarge,
    )
}

@PreviewLightDark
@Composable
private fun GameDexDescriptionInfoPreview() {
    GameDexTheme {
        Surface {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                GameDexDescriptionInfo(description = "", modifier = Modifier.fillMaxWidth())
                GameDexDescriptionInfo(
                    description = GamePlatformDetail.sampleList.first().description
                )
            }
        }
    }
}