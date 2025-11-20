package br.com.seucaio.gamedex.ui.features.platforms.list.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel.PlatformListUiEvent
import br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel.PlatformListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlatformListScreen(
    onNavigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlatformListViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val latestOnNavigateToDetail by rememberUpdatedState(onNavigateToDetail)

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is PlatformListUiEvent.NavigateToDetail -> {
                    latestOnNavigateToDetail(event.platformId)
                }
            }
        }
    }

    PlatformListContent(
        state = state,
        onAction = viewModel::handleUiAction,
        modifier = modifier
    )
}
