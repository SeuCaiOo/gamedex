package br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel

sealed interface PlatformListUiEvent {
    data class NavigateToDetail(val platformId: Int) : PlatformListUiEvent
}
