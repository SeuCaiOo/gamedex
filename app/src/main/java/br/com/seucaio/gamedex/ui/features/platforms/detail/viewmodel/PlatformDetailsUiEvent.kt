package br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel

sealed interface PlatformDetailsUiEvent {
    data object NavigateBack : PlatformDetailsUiEvent
    data class NavigateToGameDetails(val gameId: Int) : PlatformDetailsUiEvent
    data class NavigateToGamesBySearch(val query: String) : PlatformDetailsUiEvent
}