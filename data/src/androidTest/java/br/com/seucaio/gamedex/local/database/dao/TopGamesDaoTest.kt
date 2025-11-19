package br.com.seucaio.gamedex.local.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.seucaio.gamedex.local.database.GameDexDatabase
import br.com.seucaio.gamedex.local.database.entity.GamePlatformEntity
import br.com.seucaio.gamedex.local.database.entity.TopGameEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class TopGamesDaoTest {

    private lateinit var database: GameDexDatabase
    private lateinit var platformsDao: PlatformsDao
    private lateinit var topGamesDao: TopGamesDao

    private val platform1 = GamePlatformEntity(
        platformId = 1,
        name = "Platform 1",
        gamesCount = 10,
        description = "desc1"
    )
    private val topGame1 = TopGameEntity(gameId = 101, name = "Top Game 1", platformId = 1)
    private val topGame2 = TopGameEntity(gameId = 102, name = "Top Game 2", platformId = 1)

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, GameDexDatabase::class.java)
            .build()
        platformsDao = database.platformsDao()
        topGamesDao = database.topGamesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndGetTopGamesByPlatformId() = runTest {
        // Given
        platformsDao.insertAll(listOf(platform1)) // Must insert parent first
        val topGames = listOf(topGame1, topGame2)
        topGamesDao.insertAll(topGames)

        // When
        val retrievedGames = topGamesDao.getByPlatformId(1)

        // Then
        assertEquals(2, retrievedGames.size)
        val retrievedGameSignatures =
            retrievedGames.map { Triple(it.gameId, it.name, it.platformId) }.toSet()
        val expectedGameSignatures =
            topGames.map { Triple(it.gameId, it.name, it.platformId) }.toSet()
        assertEquals(expectedGameSignatures, retrievedGameSignatures)
    }

    @Test
    fun returnEmptyListIfNoTopGamesForPlatformId() = runTest {
        // Given
        platformsDao.insertAll(listOf(platform1))

        // When
        val retrievedGames = topGamesDao.getByPlatformId(1)

        // Then
        assertTrue(retrievedGames.isEmpty())
    }

    @Test
    fun deleteAllTopGames() = runTest {
        // Given
        platformsDao.insertAll(listOf(platform1))
        topGamesDao.insertAll(listOf(topGame1, topGame2))

        // When
        topGamesDao.deleteAll()
        val retrievedGames = topGamesDao.getByPlatformId(1)

        // Then
        assertTrue(retrievedGames.isEmpty())
    }

    @Test
    fun deleteTopGamesWhenParentPlatformIsDeleted() = runTest {
        // Given
        platformsDao.insertAll(listOf(platform1))
        topGamesDao.insertAll(listOf(topGame1, topGame2))

        // When
        platformsDao.deleteAll() // Deleting the parent platform
        val retrievedGames = topGamesDao.getByPlatformId(1)

        // Then
        assertTrue("Top games should be deleted on cascade", retrievedGames.isEmpty())
    }
}
