package br.com.seucaio.gamedex.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun GameDexRatingCard(
    label: String,
    score: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = label, fontSize = 12.sp)
                Text(
                    text = score,
                    color = MaterialTheme.colorScheme.surfaceTint,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun GameDexRatingCardPreview() {
    GameDexTheme {
        Surface {
            GameDexRatingCard(
                label = stringResource(R.string.metacritic),
                score = "88",
            )
        }
    }
}
