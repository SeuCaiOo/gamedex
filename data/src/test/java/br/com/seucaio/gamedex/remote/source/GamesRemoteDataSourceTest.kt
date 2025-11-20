package br.com.seucaio.gamedex.remote.source

import br.com.seucaio.gamedex.core.TestCoroutineRule
import br.com.seucaio.gamedex.core.network.ConnectivityChecker
import br.com.seucaio.gamedex.remote.dto.game.GameDetailResponse
import br.com.seucaio.gamedex.remote.dto.game.GameItemResponse
import br.com.seucaio.gamedex.remote.dto.list.GameListResponse
import br.com.seucaio.gamedex.remote.service.GameDexApiService
import br.com.seucaio.gamedex.util.exception.DomainException
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class GamesRemoteDataSourceTest {

    @get:Rule
    val testDispatcherRule = TestCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    private lateinit var apiService: GameDexApiService

    @MockK
    private lateinit var connectivityChecker: ConnectivityChecker

    private lateinit var dataSource: GamesRemoteDatSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = GamesRemoteDataSourceImpl(
            apiService = apiService,
            connectivityChecker = connectivityChecker,
            ioDispatcher = testDispatcherRule.dispatcher
        )
    }

    // region searchByPlatform()

    @Test
    fun `should return success when searchByPlatform is called`() = runTest {
        // Given
        val expectedResponse = GameListResponse(
            count = 1,
            results = listOf(GameItemResponse(id = 1, name = "Game 1"))
        )
        coEvery { apiService.searchGamesByPlatform("test", 1) } returns expectedResponse
        every { connectivityChecker.isNetworkAvailable } returns true

        // When
        val result = dataSource.searchByPlatform("test", 1)

        // Then
        assertEquals(expectedResponse, result)
        coVerify(exactly = 1) { apiService.searchGamesByPlatform("test", 1) }
    }

    @Test
    fun `should throw exception when searchByPlatform call fails`() = runTest {
        // Given
        val expectedException = RuntimeException("API Error")
        coEvery { apiService.searchGamesByPlatform("test", 1) } throws expectedException
        every { connectivityChecker.isNetworkAvailable } returns true

        // When / Then
        assertFailsWith<DomainException.UnknownException> {
            dataSource.searchByPlatform("test", 1)
        }
        coVerify(exactly = 1) { apiService.searchGamesByPlatform("test", 1) }
    }

    @Test
    fun `should throw NetworkUnavailableException when searchByPlatform call fails and network is unavailable`() = runTest {
        // Given
        val apiException = RuntimeException("API Error")
        coEvery { apiService.searchGamesByPlatform("test", 1) } throws apiException
        every { connectivityChecker.isNetworkAvailable } returns false

        // When / Then
        assertFailsWith<DomainException.NetworkUnavailableException> {
            dataSource.searchByPlatform("test", 1)
        }

        coVerify(exactly = 1) { apiService.searchGamesByPlatform(any(), any()) }
        coVerify(exactly = 1) { connectivityChecker.isNetworkAvailable }
    }

    // endregion

    // region getById()

    @Test
    fun `should return success when getById is called`() = runTest {
        // Given
        val gameId = 1
        val expectedResponse = GameDetailResponse(
            id = gameId,
            name = "Game 1",
            description = "A great game",
        )
        coEvery { apiService.getGameById(gameId) } returns expectedResponse
        every { connectivityChecker.isNetworkAvailable } returns true

        // When
        val result = dataSource.getById(gameId)

        // Then
        assertEquals(expectedResponse, result)
        coVerify(exactly = 1) { apiService.getGameById(gameId) }
    }

    @Test
    fun `should throw exception when getById call fails`() = runTest {
        // Given
        val gameId = 1
        val expectedException = RuntimeException("API Error")
        coEvery { apiService.getGameById(gameId) } throws expectedException
        every { connectivityChecker.isNetworkAvailable } returns true

        // When / Then
        assertFailsWith<DomainException.UnknownException> {
            dataSource.getById(gameId)
        }
        coVerify(exactly = 1) { apiService.getGameById(gameId) }
    }

    @Test
    fun `should throw NetworkUnavailableException when getById call fails and network is unavailable`() = runTest {
        // Given
        val gameId = 1
        val apiException = RuntimeException("API Error")
        coEvery { apiService.getGameById(gameId) } throws apiException
        every { connectivityChecker.isNetworkAvailable } returns false

        // When / Then
        assertFailsWith<DomainException.NetworkUnavailableException> {
            dataSource.getById(gameId)
        }

        coVerify(exactly = 1) { apiService.getGameById(any()) }
        coVerify(exactly = 1) { connectivityChecker.isNetworkAvailable }
    }

    // endregion
}
