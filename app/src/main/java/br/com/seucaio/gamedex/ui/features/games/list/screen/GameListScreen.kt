package br.com.seucaio.gamedex.ui.features.games.list.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.seucaio.gamedex.ui.features.games.list.viewmodel.GameListUiEvent
import br.com.seucaio.gamedex.ui.features.games.list.viewmodel.GameListViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GameListScreen(
    platformId: Int,
    gameQuery: String,
    onNavigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GameListViewModel = koinViewModel { parametersOf(platformId, gameQuery) }
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is GameListUiEvent.NavigateToDetail -> {
                    onNavigateToDetail(event.platformId)
                }
            }
        }
    }

    GameListContent(
        state = state,
        onAction = viewModel::handleUiAction,
        modifier = modifier
    )
}