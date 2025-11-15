package br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel

import br.com.seucaio.gamedex.model.platform.GamePlatformDetail

data class PlatformDetailsUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val platformDetail: GamePlatformDetail? = null,
) {
    fun setLoading() = copy(isLoading = true, errorMessage = null)

    fun setError(error: String?) = copy(isLoading = false, errorMessage = error)

    fun setSuccess(data: GamePlatformDetail) =
        copy(isLoading = false, platformDetail = data, errorMessage = null)
}