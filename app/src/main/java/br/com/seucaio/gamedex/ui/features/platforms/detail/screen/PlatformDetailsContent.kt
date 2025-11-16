package br.com.seucaio.gamedex.ui.features.platforms.detail.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.model.platform.GamePlatformDetail
import br.com.seucaio.gamedex.ui.components.GameDexDescriptionInfo
import br.com.seucaio.gamedex.ui.components.GameDexErrorContent
import br.com.seucaio.gamedex.ui.components.GameDexGameCountInfo
import br.com.seucaio.gamedex.ui.components.GameDexLoadingContent
import br.com.seucaio.gamedex.ui.components.GameDexTitleText
import br.com.seucaio.gamedex.ui.components.GameDexTopGameListItem
import br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel.PlatformDetailsUiAction
import br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel.PlatformDetailsUiState
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun PlatformDetailsContent(
    state: PlatformDetailsUiState,
    onAction: (PlatformDetailsUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        when {
            state.platformDetail != null -> {
                PlatformDetailsSuccessContent(
                    details = state.platformDetail,
                    onGameClick = { gameId ->
                        onAction(PlatformDetailsUiAction.OnGameClick(gameId))
                    }
                )
            }

            !state.errorMessage.isNullOrBlank() -> {
                GameDexErrorContent(
                    modifier = modifier,
                    onRetry = { onAction(PlatformDetailsUiAction.RetryLoadPlatform) },
                    message = state.errorMessage
                )
            }

            state.isLoading -> GameDexLoadingContent()
        }
    }
}

@Composable
fun PlatformDetailsSuccessContent(
    details: GamePlatformDetail,
    onGameClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GameDexTitleText(title = details.name, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(16.dp))
            GameDexGameCountInfo(count = details.gamesCount)
        }

        GameDexDescriptionInfo(
            description = details.description,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Column(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            GameDexTitleText(
                modifier = Modifier.fillMaxWidth(),
                title = stringResource(R.string.top_games),
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                details.topGames.ifEmpty { item { Text(stringResource(R.string.no_top_games_found)) } }
                items(details.topGames) {
                    GameDexTopGameListItem(name = it.name, onItemClick = { onGameClick(it.id) })
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun PlatformDetailsContentSuccessPreview() {
    GameDexTheme {
        PlatformDetailsContent(
            state = PlatformDetailsUiState(
                platformDetail = GamePlatformDetail.sampleList.first { it.id == 1 }
            ),
            onAction = {}
        )
    }
}


@PreviewLightDark
@Composable
private fun PlatformDetailsContentSuccessWithoutTopGamesAndDescriptionPreview() {
    GameDexTheme {
        PlatformDetailsContent(
            state = PlatformDetailsUiState(
                platformDetail = GamePlatformDetail.sampleList.first { it.id == 2 }
            ),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun PlatformDetailsContentLoadingPreview() {
    GameDexTheme {
        PlatformDetailsContent(
            state = PlatformDetailsUiState(isLoading = true),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun PlatformDetailsContentErrorPreview() {
    GameDexTheme {
        PlatformDetailsContent(
            state = PlatformDetailsUiState(errorMessage = stringResource(R.string.please_try_again)),
            onAction = {}
        )
    }
}
