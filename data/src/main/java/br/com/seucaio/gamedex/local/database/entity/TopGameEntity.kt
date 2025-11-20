package br.com.seucaio.gamedex.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "top_games",
    foreignKeys = [
        ForeignKey(
            entity = GamePlatformEntity::class,
            parentColumns = ["platform_id"],
            childColumns = ["platform_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class TopGameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "game_id")
    val gameId: Int,
    val name: String,
    @ColumnInfo(name = "platform_id", index = true)
    val platformId: Int
)
