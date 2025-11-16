package br.com.seucaio.gamedex.ui.features.platforms.list.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.ui.components.GameDexErrorContent
import br.com.seucaio.gamedex.ui.components.GameDexListItem
import br.com.seucaio.gamedex.ui.components.GameDexLoadingContent
import br.com.seucaio.gamedex.ui.components.GameDexTitleText
import br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel.PlatformListUiAction
import br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel.PlatformListUiState
import br.com.seucaio.gamedex.ui.theme.GameDexTheme

@Composable
fun PlatformListContent(
    state: PlatformListUiState,
    onAction: (PlatformListUiAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        when {
            state.platforms != null -> {
                PlatformListSuccessContent(
                    platformList = state.platforms,
                    modifier = modifier,
                    onItemClick = { platform ->
                        onAction(PlatformListUiAction.OnPlatformClick(platform.id))
                    }
                )
            }

            !state.errorMessage.isNullOrBlank() -> {
                GameDexErrorContent(
                    modifier = modifier,
                    onRetry = { onAction(PlatformListUiAction.RetryLoadPlatforms) },
                    message = state.errorMessage
                )
            }

            state.isLoading -> GameDexLoadingContent(modifier = modifier)
        }
    }
}

@Composable
fun PlatformListSuccessContent(
    platformList: List<GamePlatform>,
    modifier: Modifier = Modifier,
    onItemClick: (GamePlatform) -> Unit = {},
) {
    platformList.ifEmpty {
        return Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            GameDexTitleText(title = stringResource(R.string.no_platforms_found))
        }
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(platformList) { model ->
            GameDexListItem(
                id = model.id,
                title = model.name,
                description = stringResource(R.string.game_count_info, model.gamesCount),
                onItemClick = { onItemClick(model) }
            )
        }
    }
}



@PreviewLightDark
@Composable
private fun PlatformListContentSuccessPreview() {
    GameDexTheme {
        PlatformListContent(
            state = PlatformListUiState(platforms = GamePlatform.sampleList),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun PlatformListContentLoadingPreview() {
    GameDexTheme {
        PlatformListContent(
            state = PlatformListUiState(isLoading = true),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun PlatformListContentErrorPreview() {
    GameDexTheme {
        PlatformListContent(
            state = PlatformListUiState(errorMessage = stringResource(R.string.please_try_again)),
            onAction = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun PlatformListContentEmptyPreview() {
    GameDexTheme {
        PlatformListContent(
            state = PlatformListUiState(platforms = emptyList()),
            onAction = {}
        )
    }
}