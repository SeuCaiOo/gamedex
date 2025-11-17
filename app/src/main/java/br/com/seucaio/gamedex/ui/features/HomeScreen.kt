package br.com.seucaio.gamedex.ui.features

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToPlatforms: () -> Unit = {},
    onNavigateToGames: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onNavigateToPlatforms, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.platforms))
        }
        Button(onNavigateToGames, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.games))
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    GameDexTheme {
        Surface {
            HomeScreen()
        }
    }
}
