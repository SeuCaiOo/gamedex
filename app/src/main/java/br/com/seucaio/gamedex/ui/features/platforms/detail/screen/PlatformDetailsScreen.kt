package br.com.seucaio.gamedex.ui.features.platforms.detail.screen

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.seucaio.gamedex.R
import br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel.PlatformDetailsUiAction
import br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel.PlatformDetailsUiEvent
import br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel.PlatformDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlatformDetailsScreen(
    platformId: Int,
    onNavigateBack: () -> Unit,
    onNavigateToGameDetails: (Int) -> Unit,
    onNavigateToGameListBySearch: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PlatformDetailsViewModel = koinViewModel { parametersOf(platformId) }
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val sheetState = rememberModalBottomSheetState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is PlatformDetailsUiEvent.NavigateBack -> onNavigateBack()
                is PlatformDetailsUiEvent.NavigateToGameDetails -> {
                    onNavigateToGameDetails(event.gameId)
                }

                is PlatformDetailsUiEvent.NavigateToGamesBySearch -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.searching_by, event.query),
                        Toast.LENGTH_SHORT
                    ).show()
                    sheetState.hide()
                    onNavigateToGameListBySearch(event.query)
                }
            }
        }
    }

    if (state.isSearchSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.handleUiAction(PlatformDetailsUiAction.DismissSearchSheet) },
            sheetState = sheetState
        ) {
            SearchBottomSheetContent(
                searchQuery = state.searchQuery,
                onAction = viewModel::handleUiAction
            )
        }
    }


    PlatformDetailsContent(
        state = state,
        onAction = viewModel::handleUiAction,
        modifier = modifier,
    )
}