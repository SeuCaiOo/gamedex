package br.com.seucaio.gamedex.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface GameDexRoute {
    @Serializable
    data object Home : GameDexRoute

    @Serializable
    data object PlatformList : GameDexRoute

    @Serializable
    data class PlatformDetails(val id: Int) : GameDexRoute

    @Serializable
    data object GameList : GameDexRoute

    @Serializable
    data class GameDetails(val id: Int) : GameDexRoute
}
