package br.com.seucaio.gamedex.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun GameDexRatingInfo(
    infos: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        GameDexTitleText(
            modifier = Modifier.fillMaxWidth(),
            title = stringResource(R.string.ratings),
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            infos.forEach { (label, value) ->
                GameDexRatingCard(
                    label = label,
                    score = value,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PreviewGameDexRatingInfo() {
    GameDexTheme {
        Surface {
            GameDexRatingInfo(
                infos = listOf(
                    stringResource(R.string.metacritic) to "88",
                    stringResource(R.string.user_score) to "9.5"
                )
            )
        }
    }
}
