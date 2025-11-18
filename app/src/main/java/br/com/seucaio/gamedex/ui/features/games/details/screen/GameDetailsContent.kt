package br.com.seucaio.gamedex.ui.features.games.details.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.model.game.GameDetail
import br.com.seucaio.gamedex.ui.components.GameDexDescriptionInfo
import br.com.seucaio.gamedex.ui.components.GameDexErrorContent
import br.com.seucaio.gamedex.ui.components.GameDexHeader
import br.com.seucaio.gamedex.ui.components.GameDexInfoRow
import br.com.seucaio.gamedex.ui.components.GameDexLoadingContent
import br.com.seucaio.gamedex.ui.components.GameDexRatingInfo
import br.com.seucaio.gamedex.ui.components.GameDexTagInfo
import br.com.seucaio.gamedex.ui.features.games.details.viewmodel.GameDetailsUiAction
import br.com.seucaio.gamedex.ui.features.games.details.viewmodel.GameDetailsUiState
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun GameDetailsContent(
    state: GameDetailsUiState,
    onAction: (GameDetailsUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        when {
            state.gameDetail != null -> {
                GameDetailsSuccessContent(details = state.gameDetail)
            }

            !state.errorMessage.isNullOrBlank() -> {
                GameDexErrorContent(
                    modifier = Modifier,
                    onRetry = { onAction(GameDetailsUiAction.RetryLoadGame) },
                    message = state.errorMessage
                )
            }

            state.isLoading -> GameDexLoadingContent()
        }
    }
}

@Composable
fun GameDetailsSuccessContent(
    details: GameDetail,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()

    Column(modifier = modifier.fillMaxSize()) {
        GameDexHeader(
            title = details.name,
            backgroundImageUrl = details.imageBackground
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {
            GameDexTagInfo(
                modifier = Modifier.padding(top = 16.dp),
                info = details.esrbRatingInfo?.name
            )

            Column(modifier = Modifier.padding(bottom = 16.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                GameDexInfoRow(
                    label = stringResource(R.string.release_date),
                    value = details.released
                )
                GameDexInfoRow(
                    label = stringResource(R.string.developer),
                    value = details.developersInfo.firstOrNull()?.name
                )
                GameDexInfoRow(
                    label = stringResource(R.string.publisher),
                    value = details.publishersInfo.firstOrNull()?.name
                )
                GameDexInfoRow(
                    label = stringResource(R.string.genre),
                    value = details.genresInfo.firstOrNull()?.name
                )
            }

            GameDexDescriptionInfo(
                description = details.description,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            GameDexRatingInfo(
                infos = listOf(
                    stringResource(R.string.metacritic) to details.metacritic.toString(),
                    stringResource(R.string.user_score) to details.rating.toString()
                ),
                modifier = Modifier.padding(vertical = 16.dp),
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun GameDetailsContentSuccessPreview() {
    GameDexTheme {
        GameDetailsContent(
            state = GameDetailsUiState(
                gameDetail = GameDetail.sampleList.firstOrNull()
            ),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun GameDetailsContentSuccessWithoutTopGamesAndDescriptionPreview() {
    GameDexTheme {
        GameDetailsContent(
            state = GameDetailsUiState(
                gameDetail = GameDetail.sampleList.lastOrNull()
            ),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun GameDetailsContentLoadingPreview() {
    GameDexTheme {
        GameDetailsContent(
            state = GameDetailsUiState(isLoading = true),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun GameDetailsContentErrorPreview() {
    GameDexTheme {
        GameDetailsContent(
            state = GameDetailsUiState(errorMessage = stringResource(R.string.please_try_again)),
            onAction = {}
        )
    }
}
