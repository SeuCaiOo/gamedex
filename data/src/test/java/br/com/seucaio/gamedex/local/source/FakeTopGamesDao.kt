package br.com.seucaio.gamedex.local.source

import br.com.seucaio.gamedex.local.database.dao.TopGamesDao
import br.com.seucaio.gamedex.local.database.entity.TopGameEntity

class FakeTopGamesDao : TopGamesDao {

    private val topGames = mutableListOf<TopGameEntity>()

    override suspend fun insertAll(topGames: List<TopGameEntity>) {
        this.topGames.addAll(topGames)
    }

    override suspend fun getByPlatformId(platformId: Int): List<TopGameEntity> {
        return topGames.filter { it.platformId == platformId }
    }

    override suspend fun deleteAll() {
        topGames.clear()
    }
}
