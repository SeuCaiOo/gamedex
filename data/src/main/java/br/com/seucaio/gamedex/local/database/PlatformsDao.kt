package br.com.seucaio.gamedex.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlatformsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(platforms: List<GamePlatformEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(platform: GamePlatformEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(platform: GamePlatformEntity)

    @Query("SELECT * FROM platforms")
    suspend fun getAll(): List<GamePlatformEntity>

    @Query("SELECT * FROM platforms WHERE id = :id")
    suspend fun getById(id: Int): GamePlatformEntity?

    @Query("SELECT * FROM platforms WHERE platform_id = :platformId")
    suspend fun getByPlatformId(platformId: Int): GamePlatformEntity?

    @Query("DELETE FROM platforms")
    suspend fun deleteAll()
}