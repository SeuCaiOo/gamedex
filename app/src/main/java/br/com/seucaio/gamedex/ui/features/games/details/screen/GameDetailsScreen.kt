package br.com.seucaio.gamedex.ui.features.games.details.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun GameDetailsScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Surface(modifier = modifier) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {

        }
    }
}

@PreviewLightDark
@Composable
private fun GameDetailsScreenPreview() {
    GameDexTheme {
        GameDetailsScreen(
            gameId = 1,
            onNavigateBack = {}
        )
    }
}