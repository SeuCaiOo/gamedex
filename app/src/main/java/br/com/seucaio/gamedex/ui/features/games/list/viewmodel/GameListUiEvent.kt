package br.com.seucaio.gamedex.ui.features.games.list.viewmodel

sealed interface GameListUiEvent {
    data class NavigateToDetail(val platformId: Int) : GameListUiEvent
}
