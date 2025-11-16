package br.com.seucaio.gamedex.mapper

import br.com.seucaio.gamedex.local.database.entity.TopGameEntity
import br.com.seucaio.gamedex.model.data.TopGameData
import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.model.platform.GamePlatformDetail
import br.com.seucaio.gamedex.remote.dto.GameDataInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoGamesResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse

object GameDataMapper {
    fun GameDataListInfoResponse.toPlatformDomain(): GamePlatform {
        return GamePlatform(
            id = id,
            name = name,
            gamesCount = gamesCount
        )
    }

    fun List<GameDataListInfoResponse>.toPlatformDomain(): List<GamePlatform> {
        return map { it.toPlatformDomain() }
    }

    fun GameDataListInfoResponse.toTopGameDomain(): TopGameData {
        return TopGameData(id = id, name = name)
    }

    fun GameDataInfoResponse.toPlatformDetailDomain(
        topGames: List<TopGameData> = emptyList()
    ): GamePlatformDetail {
        return GamePlatformDetail(
            id = id,
            name = name,
            gamesCount = gamesCount,
            description = description,
            imageBackground = imageBackground.orEmpty(),
        ).setTopGames(topGames)
    }

    fun GameDataListInfoGamesResponse.toTopGameDomain(): TopGameData {
        return TopGameData(id = id, name = name)
    }

    fun GameDataListInfoGamesResponse.toTopGameEntity(platformId: Int): TopGameEntity {
        return TopGameEntity(gameId = id, name = name, platformId = platformId)
    }
}
