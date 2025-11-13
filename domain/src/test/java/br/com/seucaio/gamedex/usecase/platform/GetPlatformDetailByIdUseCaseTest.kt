package br.com.seucaio.gamedex.usecase.platform

import br.com.seucaio.gamedex.model.platform.GamePlatformDetail
import br.com.seucaio.gamedex.repository.PlatformsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetPlatformDetailByIdUseCaseTest {

    private val repository: PlatformsRepository = mockk()
    private val useCase = GetPlatformDetailByIdUseCase(repository)

    @Test
    fun `should return result success with platform detail when repository is successful`() =
        runTest {
            // Given
            val platformDetail =
                GamePlatformDetail(id = 1, name = "PC", description = "...", gamesCount = 100)
            val successResult: Result<GamePlatformDetail> = Result.success(platformDetail)
            coEvery { repository.getById(1) } returns successResult

            // When
            val result: Result<GamePlatformDetail> = useCase(1)

            // Then
            assertTrue(result.isSuccess)
            assertEquals(successResult, result)
            assertEquals(platformDetail, result.getOrNull())
        }

    @Test
    fun `should return result failure when repository fails`() = runTest {
        // Given
        val exception = Exception()
        val failureResult: Result<GamePlatformDetail> = Result.failure(exception)
        coEvery { repository.getById(1) } returns failureResult

        // When
        val result: Result<GamePlatformDetail> = useCase(1)

        // Then
        assertTrue(result.isFailure)
        assertEquals(failureResult, result)
        assertEquals(result.exceptionOrNull(), exception)
    }
}