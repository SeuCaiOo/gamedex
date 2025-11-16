package br.com.seucaio.gamedex.ui.features.games.list.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun GameListScreen(
    onNavigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(15.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
        }
    }
}

@PreviewLightDark
@Composable
private fun GameScreenPreview() {
    GameDexTheme {
        GameListScreen(
            onNavigateToDetail = {}
        )
    }
}