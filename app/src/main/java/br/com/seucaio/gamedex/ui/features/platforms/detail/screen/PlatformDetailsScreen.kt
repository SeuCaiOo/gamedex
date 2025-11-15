package br.com.seucaio.gamedex.ui.features.platforms.detail.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel.PlatformDetailsUiEvent
import br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel.PlatformDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlatformDetailsScreen(
    platformId: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlatformDetailsViewModel = koinViewModel { parametersOf(platformId) }
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is PlatformDetailsUiEvent.NavigateBack -> onNavigateBack()
            }
        }
    }

    PlatformDetailsContent(
        state = state,
        onAction = viewModel::handleUiAction,
        modifier = modifier,
    )
}