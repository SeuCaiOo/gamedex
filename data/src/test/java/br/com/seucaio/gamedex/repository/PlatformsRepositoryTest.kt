package br.com.seucaio.gamedex.repository

import br.com.seucaio.gamedex.local.database.entity.GamePlatformEntity
import br.com.seucaio.gamedex.local.source.PlatformsLocalDataSource
import br.com.seucaio.gamedex.remote.dto.GameDataInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListResponse
import br.com.seucaio.gamedex.remote.source.PlatformsRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PlatformsRepositoryTest {

    @MockK
    private lateinit var remoteDataSource: PlatformsRemoteDataSource

    @MockK
    private lateinit var localDataSource: PlatformsLocalDataSource

    private lateinit var repository: PlatformsRepository

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        localDataSource = mockk()
        repository = PlatformsRepositoryImpl(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    // region getAll Tests

    @Test
    fun `getAll should return from local when cache is not empty`() = runTest {
        // Given
        val localPlatforms = listOf(
            GamePlatformEntity(
                platformId = 1,
                name = "Local Platform",
                gamesCount = 10,
                description = ""
            )
        )
        coEvery { localDataSource.getAll() } returns localPlatforms

        // When
        val result = repository.getAll()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("Local Platform", result.getOrNull()?.first()?.name)
        coVerify(exactly = 1) { localDataSource.getAll() }
        coVerify(exactly = 0) { remoteDataSource.getAll() }
    }

    @Test
    fun `getAll should fetch from remote and cache when local is empty`() = runTest {
        // Given
        val remotePlatforms =
            listOf(GameDataListInfoResponse(id = 2, name = "Remote Platform", gamesCount = 20))
        val remoteResponse = GameDataListResponse(count = 1, results = remotePlatforms)
        coEvery { localDataSource.getAll() } returns emptyList()
        coEvery { remoteDataSource.getAll() } returns remoteResponse
        coEvery { localDataSource.clearAndCache(any()) } just runs
        coEvery { localDataSource.clearAndCacheTopGames(any()) } just runs

        // When
        val result = repository.getAll()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrNull()?.size)
        assertEquals("Remote Platform", result.getOrNull()?.first()?.name)
        coVerify(exactly = 1) { localDataSource.getAll() }
        coVerify(exactly = 1) { remoteDataSource.getAll() }
        coVerify(exactly = 1) { localDataSource.clearAndCache(any()) }
        coVerify(exactly = 1) { localDataSource.clearAndCacheTopGames(any()) }
    }

    @Test
    fun `getAll should return failure when local is empty and remote fails`() = runTest {
        // Given
        coEvery { localDataSource.getAll() } returns emptyList()
        coEvery { remoteDataSource.getAll() } throws RuntimeException("Network Error")

        // When
        val result = repository.getAll()

        // Then
        assertTrue(result.isFailure)
        coVerify(exactly = 1) { localDataSource.getAll() }
        coVerify(exactly = 1) { remoteDataSource.getAll() }
        coVerify(exactly = 0) { localDataSource.clearAndCache(any()) }
    }

    // endregion

    // region getById Tests

    @Test
    fun `getById should return from local when description is present`() = runTest {
        // Given
        val platformId = 1
        val localPlatform = GamePlatformEntity(
            platformId = platformId,
            name = "Platform",
            gamesCount = 1,
            description = "A full description."
        )
        coEvery { localDataSource.getByPlatformId(platformId) } returns localPlatform
        coEvery { localDataSource.getTopGamesByPlatform(platformId) } returns emptyList()

        // When
        val result = repository.getById(platformId)

        // Then
        assertTrue(result.isSuccess)
        assertEquals("A full description.", result.getOrNull()?.description)
        coVerify(exactly = 1) { localDataSource.getByPlatformId(platformId) }
        coVerify(exactly = 0) { remoteDataSource.getById(any()) }
    }

    @Test
    fun `getById should fetch remote when description is blank and update local`() = runTest {
        // Given
        val platformId = 1
        val localPlatform = GamePlatformEntity(
            platformId = platformId,
            name = "Platform",
            gamesCount = 1,
            description = ""
        )
        val remoteResponse = GameDataInfoResponse(
            id = platformId,
            name = "Platform",
            gamesCount = 1,
            description = "Remote Description"
        )

        coEvery { localDataSource.getByPlatformId(platformId) } returns localPlatform
        coEvery { localDataSource.getTopGamesByPlatform(platformId) } returns emptyList()
        coEvery { remoteDataSource.getById(platformId) } returns remoteResponse
        coEvery { localDataSource.updatePlatform(any()) } just runs

        // When
        val result = repository.getById(platformId)

        // Then
        assertTrue(result.isSuccess)
        assertEquals("Remote Description", result.getOrNull()?.description)
        coVerify(exactly = 2) { localDataSource.getByPlatformId(platformId) }
        coVerify(exactly = 1) { remoteDataSource.getById(platformId) }
        coVerify(exactly = 1) { localDataSource.updatePlatform(any()) }
    }

    @Test
    fun `getById should return failure when description is blank and remote fails`() = runTest {
        // Given
        val platformId = 1
        val localPlatform = GamePlatformEntity(
            platformId = platformId,
            name = "Platform",
            gamesCount = 1,
            description = ""
        )
        coEvery { localDataSource.getByPlatformId(platformId) } returns localPlatform
        coEvery { localDataSource.getTopGamesByPlatform(platformId) } returns emptyList()
        coEvery { remoteDataSource.getById(platformId) } throws RuntimeException("Network Error")

        // When
        val result = repository.getById(platformId)

        // Then
        assertTrue(result.isFailure)
        coVerify(exactly = 1) { localDataSource.getByPlatformId(platformId) }
        coVerify(exactly = 1) { remoteDataSource.getById(platformId) }
        coVerify(exactly = 0) { localDataSource.updatePlatform(any()) }
    }

    // endregion
}