package br.com.seucaio.gamedex.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "platforms",
    indices = [Index(value = ["platform_id"], unique = true)]
)
data class GamePlatformEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "platform_id")
    val platformId: Int,
    val name: String,
    val description: String,
    @ColumnInfo(name = "games_count")
    val gamesCount: Int
) {
    fun setDescription(description: String): GamePlatformEntity = copy(description = description)
}
