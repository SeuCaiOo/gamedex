package br.com.seucaio.gamedex.local.source

import br.com.seucaio.gamedex.local.database.entity.GamePlatformEntity
import br.com.seucaio.gamedex.local.database.entity.TopGameEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PlatformsLocalDataSourceTest {

    private lateinit var platformsDao: FakePlatformsDao
    private lateinit var topGamesDao: FakeTopGamesDao
    private lateinit var localDataSource: PlatformsLocalDataSource

    private val platform1 = GamePlatformEntity(
        platformId = 1,
        name = "Platform 1",
        gamesCount = 10,
        description = "desc1"
    )
    private val platform2 = GamePlatformEntity(
        platformId = 2,
        name = "Platform 2",
        gamesCount = 20,
        description = "desc2"
    )
    private val topGame1 = TopGameEntity(gameId = 101, name = "Top Game 1", platformId = 1)
    private val topGame2 = TopGameEntity(gameId = 102, name = "Top Game 2", platformId = 1)

    @Before
    fun setUp() {
        platformsDao = FakePlatformsDao()
        topGamesDao = FakeTopGamesDao()
        localDataSource = PlatformsLocalDataSourceImpl(
            dao = platformsDao,
            topGamesDao = topGamesDao,
            ioDispatcher = Dispatchers.Unconfined
        )
    }

    @Test
    fun `getAll should return all platforms from dao`() = runTest {
        // Given
        platformsDao.insertAll(listOf(platform1, platform2))

        // When
        val result = localDataSource.getAll()

        // Then
        assertEquals(listOf(platform1, platform2), result)
    }

    @Test
    fun `getByPlatformId should return correct platform`() = runTest {
        // Given
        platformsDao.insertAll(listOf(platform1))

        // When
        val result = localDataSource.getByPlatformId(1)

        // Then
        assertEquals(platform1, result)
    }

    @Test
    fun `getByPlatformId should return null if not found`() = runTest {
        // When
        val result = localDataSource.getByPlatformId(99)

        // Then
        assertNull(result)
    }

    @Test
    fun `clearAndCache should delete all and insert new platforms`() = runTest {
        // Given
        platformsDao.insertAll(
            listOf(
                GamePlatformEntity(
                    platformId = 99,
                    name = "Old Platform",
                    gamesCount = 1,
                    description = ""
                )
            )
        )
        val newPlatforms = listOf(platform1, platform2)

        // When
        localDataSource.clearAndCache(newPlatforms)
        val result = platformsDao.getAll()

        // Then
        assertEquals(2, result.size)
        assertEquals(newPlatforms, result)
    }

    @Test
    fun `updatePlatform should call update on dao`() = runTest {
        // Given
        platformsDao.insertAll(listOf(platform1))
        val updatedPlatform = platform1.copy(name = "Updated Name")

        // When
        localDataSource.updatePlatform(updatedPlatform)
        val result = platformsDao.getByPlatformId(1)

        // Then
        assertEquals("Updated Name", result?.name)
    }

    @Test
    fun `clearAndCacheTopGames should delete all and insert new top games`() = runTest {
        // Given
        topGamesDao.insertAll(listOf(TopGameEntity(gameId = 99, name = "Old Game", platformId = 1)))
        val newTopGames = listOf(topGame1, topGame2)

        // When
        localDataSource.clearAndCacheTopGames(newTopGames)
        val result = topGamesDao.getByPlatformId(1)

        // Then
        assertEquals(2, result.size)
        assertEquals(newTopGames, result)
    }

    @Test
    fun `getTopGamesByPlatform should return correct games`() = runTest {
        // Given
        topGamesDao.insertAll(listOf(topGame1, topGame2))

        // When
        val result = localDataSource.getTopGamesByPlatform(1)

        // Then
        assertEquals(2, result.size)
        assertEquals(listOf(topGame1, topGame2), result)
    }
}
