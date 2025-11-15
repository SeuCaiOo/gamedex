package br.com.seucaio.gamedex.mapper

import br.com.seucaio.gamedex.local.database.entity.TopGameEntity
import br.com.seucaio.gamedex.model.data.TopGameData

object TopGameMapper {

    fun TopGameEntity.toDomain(): TopGameData = TopGameData(id = gameId, name = name)

    fun List<TopGameEntity>.toDomain(): List<TopGameData> = map { it.toDomain() }

}