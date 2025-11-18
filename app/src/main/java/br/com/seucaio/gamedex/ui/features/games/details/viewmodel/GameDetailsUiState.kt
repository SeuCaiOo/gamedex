package br.com.seucaio.gamedex.ui.features.games.details.viewmodel

import br.com.seucaio.gamedex.model.game.GameDetail

data class GameDetailsUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val gameDetail: GameDetail? = null,
) {
    fun setLoading() = copy(isLoading = true, errorMessage = null)

    fun setError(error: String?) = copy(isLoading = false, errorMessage = error)

    fun setSuccess(data: GameDetail) =
        copy(isLoading = false, gameDetail = data, errorMessage = null)
}
