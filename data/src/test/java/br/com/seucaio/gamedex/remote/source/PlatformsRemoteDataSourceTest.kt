package br.com.seucaio.gamedex.remote.source

import br.com.seucaio.gamedex.core.TestCoroutineRule
import br.com.seucaio.gamedex.core.network.ConnectivityChecker
import br.com.seucaio.gamedex.remote.dto.GameDataInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListResponse
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
class PlatformsRemoteDataSourceTest {

    @get:Rule
    val testDispatcherRule = TestCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    private lateinit var apiService: GameDexApiService

    @MockK
    private lateinit var connectivityChecker: ConnectivityChecker

    private lateinit var dataSource: PlatformsRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = PlatformsRemoteDataSourceImpl(
            apiService = apiService,
            connectivityChecker = connectivityChecker,
            ioDispatcher = testDispatcherRule.dispatcher
        )
    }

    // region getAll()

    @Test
    fun `should return success when getAll is called`() = runTest {
        // Given
        val expectedResponse = GameDataListResponse(
            count = 1,
            results = listOf(GameDataListInfoResponse(id = 1, name = "PC", gamesCount = 100))
        )
        coEvery { apiService.getPlatforms() } returns expectedResponse

        // When
        val result = dataSource.getAll()

        // Then
        assertEquals(expectedResponse, result)
        coVerify(exactly = 1) { apiService.getPlatforms() }
    }

    @Test
    fun `should throw exception when getAll call fails`() = runTest {
        // Given
        val expectedException = RuntimeException("API Error")
        coEvery { apiService.getPlatforms() } throws expectedException
        every { connectivityChecker.isNetworkAvailable } returns true

        // When / Then
        assertFailsWith<DomainException.UnknownException> {
            dataSource.getAll()
        }
        coVerify(exactly = 1) { apiService.getPlatforms() }
    }

    @Test
    fun `should throw NetworkUnavailableException when getAll call fails and network is unavailable`() = runTest {
        // Given
        val apiException = RuntimeException("API Error")
        coEvery { apiService.getPlatforms() } throws apiException
        every { connectivityChecker.isNetworkAvailable } returns false

        // When / Then
        assertFailsWith<DomainException.NetworkUnavailableException> {
            dataSource.getAll()
        }

        coVerify(exactly = 1) { apiService.getPlatforms() }
        coVerify(exactly = 1) { connectivityChecker.isNetworkAvailable }
    }

    // endregion

    // region getById()

    @Test
    fun `should return success when getById is called`() = runTest {
        // Given
        val platformId = 1
        val expectedResponse = GameDataInfoResponse(
            id = platformId,
            name = "Playstation",
            description = "A great console",
            gamesCount = 100
        )
        coEvery { apiService.getPlatformById(platformId) } returns expectedResponse

        // When
        val result = dataSource.getById(platformId)

        // Then
        assertEquals(expectedResponse, result)
        coVerify(exactly = 1) { apiService.getPlatformById(platformId) }
    }

    @Test
    fun `should throw exception when getById call fails`() = runTest {
        // Given
        val platformId = 1
        val expectedException = RuntimeException("API Error")
        coEvery { apiService.getPlatformById(platformId) } throws expectedException
        every { connectivityChecker.isNetworkAvailable } returns true

        // When / Then
        assertFailsWith<DomainException.UnknownException> {
            dataSource.getById(platformId)
        }
        coVerify(exactly = 1) { apiService.getPlatformById(platformId) }
    }

    @Test
    fun `should throw NetworkUnavailableException when getById call fails and network is unavailable`() = runTest {
        // Given
        val platformId = 1
        val apiException = RuntimeException("API Error")
        coEvery { apiService.getPlatformById(platformId) } throws apiException
        every { connectivityChecker.isNetworkAvailable } returns false

        // When / Then
        assertFailsWith<DomainException.NetworkUnavailableException> {
            dataSource.getById(platformId)
        }

        coVerify(exactly = 1) { apiService.getPlatformById(platformId) }
        coVerify(exactly = 1) { connectivityChecker.isNetworkAvailable }
    }

    // endregion
}
