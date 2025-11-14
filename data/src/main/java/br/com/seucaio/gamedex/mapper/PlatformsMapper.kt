package br.com.seucaio.gamedex.mapper

import br.com.seucaio.gamedex.local.database.GamePlatformEntity
import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.util.extension.EMPTY

object PlatformsMapper {

    fun GamePlatformEntity.toDomain(): GamePlatform {
        return GamePlatform(
            id = id,
            name = name,
            gamesCount = gamesCount,
        )
    }

    fun List<GamePlatformEntity>.toDomain(): List<GamePlatform> {
        return map { it.toDomain() }
    }

    fun GamePlatform.toEntity(): GamePlatformEntity {
        return GamePlatformEntity(
            platformId = id,
            name = name,
            gamesCount = gamesCount,
            description = String.EMPTY,
        )
    }

    fun List<GamePlatform>.toEntity(): List<GamePlatformEntity> {
        return map { it.toEntity() }
    }

}