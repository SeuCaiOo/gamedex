package br.com.seucaio.gamedex.remote.source

import br.com.seucaio.gamedex.remote.dto.GameDataInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListInfoResponse
import br.com.seucaio.gamedex.remote.dto.list.GameDataListResponse
import br.com.seucaio.gamedex.remote.service.GameDexApiService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class PlatformsRemoteDataSourceTest {

    @MockK
    private lateinit var apiService: GameDexApiService

    private lateinit var dataSource: PlatformsRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = PlatformsRemoteDataSourceImpl(
            apiService = apiService,
            ioDispatcher = Dispatchers.Unconfined
        )
    }

    @Test
    fun `should return success when getAll is called`() = runTest {
        // Given
        val expectedResponse = GameDataListResponse(
            count = 1,
            next = null,
            previous = null,
            results = listOf(GameDataListInfoResponse(name = "PC", id = 1, gamesCount = 500, imageBackground = ""))
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

        // When / Then
        assertFailsWith<RuntimeException> {
            dataSource.getAll()
        }
        coVerify(exactly = 1) { apiService.getPlatforms() }
    }

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

        // When / Then
        assertFailsWith<RuntimeException> {
            dataSource.getById(platformId)
        }
        coVerify(exactly = 1) { apiService.getPlatformById(platformId) }
    }
}