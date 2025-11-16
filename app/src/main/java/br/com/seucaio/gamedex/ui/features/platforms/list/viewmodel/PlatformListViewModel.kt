package br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.seucaio.gamedex.usecase.platform.GetPlatformsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlatformListViewModel(
    private val getPlatformsUseCase: GetPlatformsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PlatformListUiState())
    val uiState: StateFlow<PlatformListUiState> = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<PlatformListUiEvent>()
    val uiEvent: SharedFlow<PlatformListUiEvent> = _uiEvent.asSharedFlow()

    init {
        handleUiAction(PlatformListUiAction.GetPlatforms)
    }

    fun handleUiAction(action: PlatformListUiAction) {
        when (action) {
            is PlatformListUiAction.GetPlatforms -> getGamePlatforms()
            is PlatformListUiAction.OnPlatformClick -> navigateToDetail(action.id)
            is PlatformListUiAction.RetryLoadPlatforms -> getGamePlatforms()
        }
    }

    private fun getGamePlatforms() {
        viewModelScope.launch {
            _uiState.update { it.setLoading() }

            val result = getPlatformsUseCase()
            result.fold(
                onSuccess = { platforms -> _uiState.update { it.setSuccess(platforms) } },
                onFailure = { exception ->
                    _uiState.update { it.setError(exception.message.orEmpty()) }
                }
            )
        }
    }

    private fun navigateToDetail(platformId: Int) {
        viewModelScope.launch { _uiEvent.emit(PlatformListUiEvent.NavigateToDetail(platformId)) }
    }

}
