package br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.seucaio.gamedex.usecase.platform.GetPlatformDetailByIdUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlatformDetailsViewModel(
    private val id: Int,
    private val getPlatformByIdUseCase: GetPlatformDetailByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlatformDetailsUiState())
    val uiState: StateFlow<PlatformDetailsUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<PlatformDetailsUiEvent>()
    val uiEvent: SharedFlow<PlatformDetailsUiEvent> = _uiEvent.asSharedFlow()

    init {
        handleUiAction(PlatformDetailsUiAction.GetPlatformById(id))
    }

    fun handleUiAction(action: PlatformDetailsUiAction) {
        when (action) {
            is PlatformDetailsUiAction.GetPlatformById -> getGamePlatformById()
            is PlatformDetailsUiAction.RetryLoadPlatform -> getGamePlatformById()
            is PlatformDetailsUiAction.OnBackClick -> navigateBack()
            is PlatformDetailsUiAction.OnGameClick -> navigateToGameDetails(action.gameId)
        }
    }

    private fun getGamePlatformById() {
        viewModelScope.launch {
            _uiState.update { it.setLoading() }

            getPlatformByIdUseCase(id).fold(
                onSuccess = { platform -> _uiState.update { it.setSuccess(platform) } },
                onFailure = { exception -> _uiState.update { it.setError(exception.message) } }
            )
        }
    }

    private fun navigateBack() {
        viewModelScope.launch { _uiEvent.emit(PlatformDetailsUiEvent.NavigateBack) }
    }

    private fun navigateToGameDetails(gameId: Int) {
        viewModelScope.launch {
            _uiEvent.emit(PlatformDetailsUiEvent.NavigateToGameDetails(gameId))
        }
    }
}

