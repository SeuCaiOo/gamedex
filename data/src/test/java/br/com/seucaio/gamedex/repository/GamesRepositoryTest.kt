package br.com.seucaio.gamedex.repository

import br.com.seucaio.gamedex.remote.dto.game.GameDetailResponse
import br.com.seucaio.gamedex.remote.dto.game.GameItemResponse
import br.com.seucaio.gamedex.remote.dto.list.GameListResponse
import br.com.seucaio.gamedex.remote.source.GamesRemoteDatSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
class GamesRepositoryTest {

    private lateinit var remoteDataSource: GamesRemoteDatSource
    private lateinit var repository: GamesRepository

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        repository = GamesRepositoryImpl(remoteDataSource)
    }

    // region searchByPlatform()

    @Test
    fun `searchByPlatform should return success with mapped items`() = runTest {
        // Given
        val query = "zelda"
        val platformId = 1
        val apiResponse = GameListResponse(
            count = 1,
            results = listOf(
                GameItemResponse(
                    id = 10,
                    name = "Zelda Game"
                )
            )
        )
        coEvery { remoteDataSource.searchByPlatform(query, platformId) } returns apiResponse

        // When
        val result = repository.searchByPlatform(query, platformId)

        // Then
        assertTrue(result.isSuccess)
        val list = result.getOrNull()
        assertEquals(1, list?.size)
        assertEquals("Zelda Game", list?.first()?.name)
        assertEquals(10, list?.first()?.id)
        coVerify(exactly = 1) { remoteDataSource.searchByPlatform(query, platformId) }
    }

    @Test
    fun `searchByPlatform should return failure when remote throws`() = runTest {
        // Given
        val query = "mario"
        val platformId = 4
        coEvery { remoteDataSource.searchByPlatform(query, platformId) } throws RuntimeException("API Error")

        // When
        val result = repository.searchByPlatform(query, platformId)

        // Then
        assertTrue(result.isFailure)
        coVerify(exactly = 1) { remoteDataSource.searchByPlatform(query, platformId) }
    }

    // endregion

    // region getById()

    @Test
    fun `getById should return success with mapped detail`() = runTest {
        // Given
        val gameId = 99
        val apiResponse = GameDetailResponse(
            id = gameId,
            name = "Awesome Game",
            description = "An awesome game description"
        )
        coEvery { remoteDataSource.getById(gameId) } returns apiResponse

        // When
        val result = repository.getById(gameId)

        // Then
        assertTrue(result.isSuccess)
        val detail = result.getOrNull()
        assertEquals(gameId, detail?.id)
        assertEquals("Awesome Game", detail?.name)
        coVerify(exactly = 1) { remoteDataSource.getById(gameId) }
    }

    @Test
    fun `getById should return failure when remote throws`() = runTest {
        // Given
        val gameId = 7
        coEvery { remoteDataSource.getById(gameId) } throws RuntimeException("API Error")

        // When
        val result = repository.getById(gameId)

        // Then
        assertTrue(result.isFailure)
        coVerify(exactly = 1) { remoteDataSource.getById(gameId) }
    }

    // endregion
}
