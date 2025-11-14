package br.com.seucaio.gamedex.mapper

import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.model.platform.GamePlatformDetail
import br.com.seucaio.gamedex.remote.dto.GameDataInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse
import kotlin.collections.map

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

    fun GameDataInfoResponse.toPlatformDetailDomain(): GamePlatformDetail {
        return GamePlatformDetail(
            id = id,
            name = name,
            gamesCount = gamesCount,
            description = description,
            imageBackground = imageBackground.orEmpty(),
        )
    }
}