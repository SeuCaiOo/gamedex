package br.com.seucaio.gamedex.ui.features.games.list.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.model.game.GameItem
import br.com.seucaio.gamedex.ui.components.GameDexErrorContent
import br.com.seucaio.gamedex.ui.components.GameDexGridItem
import br.com.seucaio.gamedex.ui.components.GameDexListItem
import br.com.seucaio.gamedex.ui.components.GameDexLoadingContent
import br.com.seucaio.gamedex.ui.components.GameDexTitleText
import br.com.seucaio.gamedex.ui.features.games.list.viewmodel.GameListUiAction
import br.com.seucaio.gamedex.ui.features.games.list.viewmodel.GameListUiState
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun GameListContent(
    modifier: Modifier = Modifier,
    state: GameListUiState,
    onAction: (GameListUiAction) -> Unit
) {
    Surface(modifier = modifier) {
        when {
            state.gameItems != null -> {
                GameListSuccessContent(
                    gameList = state.gameItems,
                    modifier = modifier,
                    onItemClick = { platform ->
                        onAction(GameListUiAction.OnGameClick(platform.id))
                    }
                )
            }

            !state.errorMessage.isNullOrBlank() -> {
                GameDexErrorContent(
                    modifier = modifier,
                    onRetry = { onAction(GameListUiAction.RetryLoadGames) },
                    message = state.errorMessage
                )
            }

            state.isLoading -> GameDexLoadingContent(modifier = modifier)
        }
    }
}


@Composable
fun GameListSuccessContent(
    gameList: List<GameItem>,
    modifier: Modifier = Modifier,
    onItemClick: (GameItem) -> Unit = {},
) {
    gameList.ifEmpty {
        return Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            GameDexTitleText(title = stringResource(R.string.no_games_found))
        }
    }

    LazyVerticalGrid(
        columns = androidx.compose.foundation.lazy.grid.GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier
    ) {
        items(gameList) { model ->
            GameDexGridItem(
                id = model.id,
                title = model.name,
                backgroundImageUrl = model.imageBackground,
                onItemClick = { onItemClick(model) }
            )
        }
    }
}



@PreviewLightDark
@Composable
private fun GameListContentSuccessPreview() {
    GameDexTheme {
        GameListContent(
            state = GameListUiState(gameItems = GameItem.sampleList),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun GameListContentLoadingPreview() {
    GameDexTheme {
        GameListContent(
            state = GameListUiState(isLoading = true),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun GameListContentErrorPreview() {
    GameDexTheme {
        GameListContent(
            state = GameListUiState(errorMessage = stringResource(R.string.please_try_again)),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun GameListContentEmptyPreview() {
    GameDexTheme {
        GameListContent(
            state = GameListUiState(gameItems = emptyList()),
            onAction = {}
        )
    }
}