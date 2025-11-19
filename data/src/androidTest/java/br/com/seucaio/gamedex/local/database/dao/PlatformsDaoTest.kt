package br.com.seucaio.gamedex.local.database.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.seucaio.gamedex.local.database.GameDexDatabase
import br.com.seucaio.gamedex.local.database.entity.GamePlatformEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class PlatformsDaoTest {

    private lateinit var database: GameDexDatabase
    private lateinit var platformsDao: PlatformsDao

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

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, GameDexDatabase::class.java)
            .build()
        platformsDao = database.platformsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndGetAllPlatforms() = runTest {
        // Given
        val platforms = listOf(platform1, platform2)
        platformsDao.insertAll(platforms)

        // When
        val allPlatforms = platformsDao.getAll()

        // Then
        assertEquals(2, allPlatforms.size)
        // Compare content, ignoring the auto-generated id
        assertTrue(allPlatforms.any { it.platformId == platform1.platformId && it.name == platform1.name })
        assertTrue(allPlatforms.any { it.platformId == platform2.platformId && it.name == platform2.name })
    }

    @Test
    fun getPlatformByPlatformId() = runTest {
        // Given
        platformsDao.insertAll(listOf(platform1))

        // When
        val retrievedPlatform = platformsDao.getByPlatformId(1)

        // Then
        assertNotNull(retrievedPlatform)
        assertEquals(platform1.platformId, retrievedPlatform!!.platformId)
        assertEquals(platform1.name, retrievedPlatform.name)
    }

    @Test
    fun returnNullWhenPlatformNotFound() = runTest {
        // When
        val retrievedPlatform = platformsDao.getByPlatformId(99)

        // Then
        assertNull(retrievedPlatform)
    }

    @Test
    fun updateAPlatform() = runTest {
        // Given
        platformsDao.insertAll(listOf(platform1))
        val insertedPlatform = platformsDao.getByPlatformId(platform1.platformId)
        assertNotNull(insertedPlatform)

        // When
        val updatedPlatform = insertedPlatform!!.copy(description = "New Description")
        platformsDao.update(updatedPlatform)
        val retrievedPlatform = platformsDao.getByPlatformId(platform1.platformId)

        // Then
        assertEquals(updatedPlatform, retrievedPlatform)
        assertEquals("New Description", retrievedPlatform?.description)
    }

    @Test
    fun deleteAllPlatforms() = runTest {
        // Given
        platformsDao.insertAll(listOf(platform1, platform2))

        // When
        platformsDao.deleteAll()
        val allPlatforms = platformsDao.getAll()

        // Then
        assertTrue(allPlatforms.isEmpty())
    }
}
