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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(topGame: TopGameEntity)

    @Query("SELECT * FROM top_games")
    suspend fun getAll(): List<TopGameEntity>

    @Query("SELECT * FROM top_games WHERE id = :id")
    suspend fun getById(id: Int): TopGameEntity?

    @Query("SELECT * FROM top_games WHERE game_id = :gameId")
    suspend fun getByGameId(gameId: Int): TopGameEntity?

    @Query("SELECT * FROM top_games WHERE platform_id = :platformId")
    suspend fun getByPlatformId(platformId: Int): List<TopGameEntity>

    @Query("DELETE FROM top_games")
    suspend fun deleteAll()

    @Query("DELETE FROM top_games WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM top_games WHERE game_id = :gameId")
    suspend fun deleteByGameId(gameId: Int)
}