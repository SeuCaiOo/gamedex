package br.com.seucaio.gamedex.model.game

import br.com.seucaio.gamedex.model.data.GameDataDetail
import br.com.seucaio.gamedex.model.genre.GameGenreDetail
import br.com.seucaio.gamedex.util.extension.EMPTY
import br.com.seucaio.gamedex.util.extension.ZERO

data class GameDetail(
    override val id: Int,
    override val name: String,
    override val imageBackground: String = String.EMPTY,
    val imageBackgroundAdditional: String = String.EMPTY,
    val description: String = String.EMPTY,
    val metaCritic: Int = Int.ZERO,
    val released: String = String.EMPTY,
    val website: String = String.EMPTY,
    val rating: Double = Double.ZERO,
    val shortScreenshots: List<GameScreenshot> = emptyList(),
    val alternativeNames: List<String> = emptyList(),
    val platformsInfo: List<GamePlatformDetailInfo> = emptyList(),
    val storesInfo: List<GameStoreDetailInfo> = emptyList(),
    val developersInfo: List<GameDeveloperDetail> = emptyList(),
    val publishersInfo: List<GamePublisherDetail> = emptyList(),
    val genresInfo: List<GameGenreDetail> = emptyList(),
    val esrbRatingInfo: GameEsrbRatingDetail? = null,
) : GameDataDetail {

}