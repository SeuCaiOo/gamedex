package br.com.seucaio.gamedex.ui.features.games.details.viewmodel

sealed interface GameDetailsUiEvent {
    data object NavigateBack : GameDetailsUiEvent
}