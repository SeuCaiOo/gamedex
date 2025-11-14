package br.com.seucaio.gamedex.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GamePlatformEntity::class], version = 1, exportSchema = false)
abstract class GameDexDatabase : RoomDatabase() {
    abstract fun platformsDao(): PlatformsDao

    companion object {
        @Volatile
        private var INSTANCE: GameDexDatabase? = null
        const val DATABASE_NAME = "gamedex_app_database"

        fun getDatabase(context: Context): GameDexDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = GameDexDatabase::class.java,
                    name = DATABASE_NAME
                )
                    .fallbackToDestructiveMigration(false)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}