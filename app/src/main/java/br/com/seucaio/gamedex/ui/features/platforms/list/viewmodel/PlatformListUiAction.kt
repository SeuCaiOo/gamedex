package br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel

sealed interface PlatformListUiAction {
    object GetPlatforms : PlatformListUiAction
    object RetryLoadPlatforms : PlatformListUiAction
    data class OnPlatformClick(val id: Int) : PlatformListUiAction
}
