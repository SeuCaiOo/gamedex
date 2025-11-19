package br.com.seucaio.gamedex.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.seucaio.gamedex.local.database.entity.TopGameEntity

@Dao
interface TopGamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(topGames: List<TopGameEntity>)

    @Query("SELECT * FROM top_games WHERE platform_id = :platformId")
    suspend fun getByPlatformId(platformId: Int): List<TopGameEntity>

    @Query("DELETE FROM top_games")
    suspend fun deleteAll()
}
