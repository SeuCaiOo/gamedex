package br.com.seucaio.gamedex.ui.features.games.list.viewmodel

sealed interface GameListUiAction {
    object GetGames : GameListUiAction
    object RetryLoadGames : GameListUiAction
    data class OnGameClick(val id: Int) : GameListUiAction
}