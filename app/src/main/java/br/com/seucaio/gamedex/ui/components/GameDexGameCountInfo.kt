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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.ui.theme.GameDexTheme
import br.com.seucaio.gamedex.util.extension.isZero

@Composable
fun GameDexGameCountInfo(
    count: Int,
    modifier: Modifier = Modifier,
) {
    val info: String = if (count.isZero()) {
        stringResource(R.string.no_games)
    } else {
        stringResource(R.string.game_count_info, count)
    }
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
private fun GameDexGameCountInfoPreview() {
    GameDexTheme {
        Surface {
            Column {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    GameDexGameCountInfo(count = 150)
                    GameDexGameCountInfo(count = 0)
                }
            }
        }
    }
}
