package br.com.seucaio.gamedex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun GameDexTagInfo(
    info: String?,
    modifier: Modifier = Modifier,
) {
    if (info.isNullOrBlank()) return

    Text(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(vertical = 8.dp, horizontal = 16.dp),
        text = info,
        style = MaterialTheme.typography.labelSmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@PreviewLightDark
@Composable
private fun GameDexTagInfoPreview() {
    GameDexTheme {
        Surface {
            Column {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    GameDexTagInfo("Tag")
                    GameDexTagInfo(null)
                }
            }
        }
    }
}
