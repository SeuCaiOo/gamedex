package br.com.seucaio.gamedex.ui.features.games.details.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.seucaio.gamedex.ui.features.games.details.viewmodel.GameDetailsUiEvent
import br.com.seucaio.gamedex.ui.features.games.details.viewmodel.GameDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun GameDetailsScreen(
    gameId: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: GameDetailsViewModel = koinViewModel { parametersOf(gameId) }
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is GameDetailsUiEvent.NavigateBack -> onNavigateBack()
            }
        }
    }

    GameDetailsContent(
        state = state,
        onAction = viewModel::handleUiAction,
        modifier = modifier,
    )
}