package br.com.seucaio.gamedex.remote.service

import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import kotlin.test.assertFailsWith

class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: GameDexApiService

    @OptIn(ExperimentalSerializationApi::class)
    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val json = Json { ignoreUnknownKeys = true }

        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(GameDexApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getPlatforms should return platform list with success`() = runTest {
        // Given
        val jsonResponse = """
            {
              "count": 1,
              "next": null,
              "previous": null,
              "results": [
                {
                  "id": 4,
                  "name": "PC",
                  "slug": "pc",
                  "games_count": 527863,
                  "image_background": "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg"
                }
              ]
            }
        """.trimIndent()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(jsonResponse)
        mockWebServer.enqueue(response)

        // When
        val result = service.getPlatforms()

        // Then
        assertNotNull(result)
        assertEquals(1, result.count)
        assertEquals(1, result.results.size)
        val platform = result.results[0]
        assertEquals("PC", platform.name)
        assertEquals(4, platform.id)
        assertEquals(527863, platform.gamesCount)
    }

    @Test
    fun `getPlatforms should throw HttpException on error`() = runTest {
        // Given
        val response = MockResponse()
            .setResponseCode(404)
        mockWebServer.enqueue(response)

        // When / Then
        assertFailsWith<HttpException> {
            service.getPlatforms()
        }
    }

    @Test
    fun `getPlatformById should return platform details with success`() = runTest {
        // Given
        val platformId = 4
        val jsonResponse = """
            {
              "id": 4,
              "name": "PC",
              "slug": "pc",
              "games_count": 527863,
              "image_background": "https://media.rawg.io/media/games/456/456dea5e1c7e3cd07060c14e96612001.jpg",
              "description": "About the platform"
            }
        """.trimIndent()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(jsonResponse)
        mockWebServer.enqueue(response)

        // When
        val result = service.getPlatformById(platformId)

        // Then
        assertNotNull(result)
        assertEquals(platformId, result.id)
        assertEquals("PC", result.name)
        assertEquals(527863, result.gamesCount)
        assertEquals("About the platform", result.description)
    }

    @Test
    fun `getPlatformById should throw HttpException on error`() = runTest {
        // Given
        val platformId = 4
        val response = MockResponse()
            .setResponseCode(500)
        mockWebServer.enqueue(response)

        // When / Then
        assertFailsWith<HttpException> {
            service.getPlatformById(platformId)
        }
    }
}