package br.com.seucaio.gamedex.mapper

import br.com.seucaio.gamedex.model.data.GameDataInfo
import br.com.seucaio.gamedex.model.game.GameDetail
import br.com.seucaio.gamedex.model.game.GameDeveloperDetail
import br.com.seucaio.gamedex.model.game.GameEsrbRatingDetail
import br.com.seucaio.gamedex.model.game.GameItem
import br.com.seucaio.gamedex.model.game.GamePlatformDetailInfo
import br.com.seucaio.gamedex.model.game.GamePublisherDetail
import br.com.seucaio.gamedex.model.game.GameScreenshot
import br.com.seucaio.gamedex.model.game.GameStoreDetailInfo
import br.com.seucaio.gamedex.model.genre.GameGenreDetail
import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.remote.dto.game.GameDetailResponse
import br.com.seucaio.gamedex.remote.dto.game.GameItemResponse
import br.com.seucaio.gamedex.remote.dto.game.GameScreenshotResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoGamesResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse
import br.com.seucaio.gamedex.util.extension.EMPTY
import br.com.seucaio.gamedex.util.extension.ZERO
import br.com.seucaio.gamedex.util.extension.orZero

object GamesMapper {
    // region GameItem (list)
    fun GameItemResponse.toDomain(): GameItem {
        return GameItem(
            id = id,
            name = name,
            imageBackground = imageBackground.orEmpty(),
            metacritic = metacritic.orZero(),
            released = released.orEmpty(),
            rating = rating.orZero(),
            platforms = platforms?.map { it.info.toInfoDomain() }.orEmpty(),
            stores = stores?.map { it.info.toInfoDomain() }.orEmpty(),
            genres = genres?.map { it.toInfoDomain() }.orEmpty(),
            esrbRating = esrbRating?.toEsrbDomain(),
            shortScreenshots = shortScreenshots?.map { it.toDomain() }.orEmpty()
        )
    }

    fun List<GameItemResponse>.toDomain(): List<GameItem> = map { it.toDomain() }
    // endregion

    // region GameDetail
    fun GameDetailResponse.toDomain(): GameDetail {
        return GameDetail(
            id = id,
            name = name,
            imageBackground = imageBackground.orEmpty(),
            imageBackgroundAdditional = imageBackgroundAdditional.orEmpty(),
            description = description,
            metacritic = metacritic.orZero(),
            released = released.orEmpty(),
            website = website.orEmpty(),
            rating = rating.orZero(),
            alternativeNames = alternativeNames.orEmpty(),
            platformsInfo = platforms?.map { it.toDomain() }.orEmpty(),
            storesInfo = stores?.map { it.toDomain() }.orEmpty(),
            developersInfo = developers?.map { it.toDeveloperDomain() }.orEmpty(),
            publishersInfo = publishers?.map { it.toPublisherDomain() }.orEmpty(),
            genresInfo = genres?.map { it.toGenreDomain() }.orEmpty(),
            esrbRatingInfo = esrbRating?.toEsrbDomain()
        )
    }
    // endregion

    // region Helpers
    private fun GameScreenshotResponse.toDomain(): GameScreenshot {
        return GameScreenshot(id = id, image = image)
    }

    private fun GameDataListInfoGamesResponse.toInfoDomain(): GameDataInfo {
        return GameDataInfo(id = id, name = name)
    }

    private fun GameDataListInfoGamesResponse.toEsrbDomain(): GameEsrbRatingDetail {
        return GameEsrbRatingDetail(id = id, name = name)
    }

    private fun GameDataListInfoResponse.toPlatformDomain(): GamePlatform {
        return GamePlatform(
            id = id,
            name = name,
            gamesCount = gamesCount.orZero()
        )
    }

    private fun GameDataListInfoResponse.toDeveloperDomain(): GameDeveloperDetail {
        return GameDeveloperDetail(
            id = id,
            name = name,
            imageBackground = imageBackground.orEmpty(),
            gamesCount = gamesCount.orZero()
        )
    }

    private fun GameDataListInfoResponse.toPublisherDomain(): GamePublisherDetail {
        return GamePublisherDetail(
            id = id,
            name = name,
            imageBackground = imageBackground.orEmpty(),
            gamesCount = gamesCount.orZero()
        )
    }

    private fun GameDataListInfoResponse.toGenreDomain(): GameGenreDetail {
        return GameGenreDetail(
            id = id,
            name = name,
            imageBackground = imageBackground.orEmpty(),
            description = String.EMPTY,
            gamesCount = gamesCount.orZero()
        )
    }

    private fun GameDetailResponse.PlatformInfoDetailResponse<GameDataListInfoResponse>.toDomain(): GamePlatformDetailInfo {
        val req = requirements
        return GamePlatformDetailInfo(
            platformItem = info.toPlatformDomain(),
            releasedAt = releasedAt.orEmpty(),
            requirements = if (req != null) {
                GamePlatformDetailInfo.PlatformRequirements(
                    minimum = req.minimum.orEmpty(),
                    recommended = req.recommended.orEmpty()
                )
            } else null
        )
    }

    private fun GameDetailResponse.StoreInfoDetailResponse.toDomain(): GameStoreDetailInfo {
        val s = info
        return GameStoreDetailInfo(
            id = id,
            storeItem = GameStoreDetailInfo.StoreItem(
                id = s.id,
                name = s.name,
                domain = s.domain,
                gamesCount = s.gamesCount.orZero(),
                imageBackground = s.imageBackground.orEmpty()
            )
        )
    }
    // endregion
}