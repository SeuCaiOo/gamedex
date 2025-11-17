package br.com.seucaio.gamedex.ui.features.games.details.viewmodel

sealed interface GameDetailsUiAction {
    data class GetGameById(val id: Int) : GameDetailsUiAction
    data object RetryLoadGame : GameDetailsUiAction
    data object OnBackClick : GameDetailsUiAction
}