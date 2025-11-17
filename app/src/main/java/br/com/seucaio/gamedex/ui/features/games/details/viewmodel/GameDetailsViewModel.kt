package br.com.seucaio.gamedex.ui.features.games.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.seucaio.gamedex.usecase.game.GetGameDetailByIdUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameDetailsViewModel(
    private val id: Int,
    private val getGameDetailByIdUseCase: GetGameDetailByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(GameDetailsUiState())
    val uiState: StateFlow<GameDetailsUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<GameDetailsUiEvent>()
    val uiEvent: SharedFlow<GameDetailsUiEvent> = _uiEvent.asSharedFlow()

    init {
        handleUiAction(GameDetailsUiAction.GetGameById(id))
    }

    fun handleUiAction(action: GameDetailsUiAction) {
        when (action) {
            is GameDetailsUiAction.GetGameById -> getGamePlatformById()
            is GameDetailsUiAction.RetryLoadGame -> getGamePlatformById()
            is GameDetailsUiAction.OnBackClick -> navigateBack()
        }
    }

    private fun getGamePlatformById() {
        viewModelScope.launch {
            _uiState.update { it.setLoading() }

            getGameDetailByIdUseCase(id).fold(
                onSuccess = { platform -> _uiState.update { it.setSuccess(platform) } },
                onFailure = { exception -> _uiState.update { it.setError(exception.message) } }
            )
        }
    }

    private fun navigateBack() {
        viewModelScope.launch { _uiEvent.emit(GameDetailsUiEvent.NavigateBack) }
    }
}