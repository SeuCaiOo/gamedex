package br.com.seucaio.gamedex.ui.features.games.list.viewmodel

import br.com.seucaio.gamedex.model.game.GameItem

data class GameListUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val gameItems: List<GameItem>? = null
) {
    fun setLoading() = copy(isLoading = true, errorMessage = null)

    fun setError(error: String) = copy(isLoading = false, errorMessage = error)

    fun setSuccess(games: List<GameItem>) =
        copy(isLoading = false, gameItems = games, errorMessage = null)
}