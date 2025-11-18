package br.com.seucaio.gamedex.ui.features.games.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.seucaio.gamedex.usecase.game.GetGamesByPlatformByIdUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameListViewModel(
    private val platformId: Int,
    private val gameQuery: String,
    private val getGamesByPlatformByIdUseCase: GetGamesByPlatformByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameListUiState())
    val uiState: StateFlow<GameListUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<GameListUiEvent>()
    val uiEvent: SharedFlow<GameListUiEvent> = _uiEvent.asSharedFlow()

    init {
        handleUiAction(GameListUiAction.GetGames)
    }

    fun handleUiAction(action: GameListUiAction) {
        when (action) {
            is GameListUiAction.GetGames -> getGamePlatforms()
            is GameListUiAction.OnGameClick -> navigateToDetail(action.id)
            is GameListUiAction.RetryLoadGames -> getGamePlatforms()
        }
    }

    private fun getGamePlatforms() {
        viewModelScope.launch {
            _uiState.update { it.setLoading() }

            val result = getGamesByPlatformByIdUseCase(
                platformId = platformId,
                query = gameQuery
            )
            result.fold(
                onSuccess = { platforms -> _uiState.update { it.setSuccess(platforms) } },
                onFailure = { exception ->
                    _uiState.update { it.setError(exception.message.orEmpty()) }
                }
            )
        }
    }

    private fun navigateToDetail(platformId: Int) {
        viewModelScope.launch { _uiEvent.emit(GameListUiEvent.NavigateToDetail(platformId)) }
    }
}
