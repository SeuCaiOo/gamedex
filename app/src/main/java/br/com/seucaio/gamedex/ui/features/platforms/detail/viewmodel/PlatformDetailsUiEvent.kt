package br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel

sealed interface PlatformDetailsUiEvent {
    data object NavigateBack : PlatformDetailsUiEvent
}