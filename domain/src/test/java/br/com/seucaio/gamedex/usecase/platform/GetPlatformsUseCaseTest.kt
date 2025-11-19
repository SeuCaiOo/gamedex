package br.com.seucaio.gamedex.usecase.platform

import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.repository.PlatformsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetPlatformsUseCaseTest {

    private val repository: PlatformsRepository = mockk()
    private val useCase = GetPlatformsUseCase(repository)

    @Test
    fun `should return result success with platforms when repository is successful`() = runTest {
        // Given
        val platforms: List<GamePlatform> = listOf(
            GamePlatform(id = 1, name = "PC", gamesCount = 100),
            GamePlatform(id = 2, name = "Xbox One", gamesCount = 200),
        )
        val successResult: Result<List<GamePlatform>> = Result.success(platforms)
        coEvery { repository.getAll() } returns successResult

        // When
        val result: Result<List<GamePlatform>> = useCase()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(successResult, result)
        assertEquals(platforms, result.getOrNull())
    }

    @Test
    fun `should return result failure when repository fails`() = runTest {
        // Given
        val exception = Exception()
        val failureResult: Result<List<GamePlatform>> = Result.failure(exception)
        coEvery { repository.getAll() } returns failureResult

        // When
        val result: Result<List<GamePlatform>> = useCase()

        // Then
        assertTrue(result.isFailure)
        assertEquals(failureResult, result)
        assertEquals(result.exceptionOrNull(), exception)
    }
}
