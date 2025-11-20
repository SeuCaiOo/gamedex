package br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel

import br.com.seucaio.gamedex.model.platform.GamePlatform

data class PlatformListUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val platforms: List<GamePlatform>? = null
) {
    fun setLoading() = copy(isLoading = true, errorMessage = null)

    fun setError(error: String) = copy(isLoading = false, errorMessage = error)

    fun setSuccess(platforms: List<GamePlatform>) =
        copy(isLoading = false, platforms = platforms, errorMessage = null)
}
