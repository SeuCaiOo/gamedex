package br.com.seucaio.gamedex.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.com.seucaio.gamedex.local.database.entity.GamePlatformEntity

@Dao
interface PlatformsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(platforms: List<GamePlatformEntity>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(platform: GamePlatformEntity)

    @Query("SELECT * FROM platforms")
    suspend fun getAll(): List<GamePlatformEntity>

    @Query("SELECT * FROM platforms WHERE platform_id = :platformId")
    suspend fun getByPlatformId(platformId: Int): GamePlatformEntity?

    @Query("DELETE FROM platforms")
    suspend fun deleteAll()
}
