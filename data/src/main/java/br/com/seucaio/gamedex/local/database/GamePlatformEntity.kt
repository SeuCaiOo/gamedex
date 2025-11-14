package br.com.seucaio.gamedex.local.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "platforms")
data class GamePlatformEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "platform_id")
    val platformId: Int,
    val name: String,
    val description: String,
    @ColumnInfo(name = "games_count")
    val gamesCount: Int
)