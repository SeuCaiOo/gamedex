package br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel

sealed interface PlatformDetailsUiAction {
    data class GetPlatformById(val id: Int) : PlatformDetailsUiAction
    data object RetryLoadPlatform : PlatformDetailsUiAction
    data object OnBackClick : PlatformDetailsUiAction
    data class OnGameClick(val gameId: Int) : PlatformDetailsUiAction
}