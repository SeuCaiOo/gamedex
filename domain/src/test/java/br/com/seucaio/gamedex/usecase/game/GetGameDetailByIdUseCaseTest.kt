package br.com.seucaio.gamedex.usecase.game

import br.com.seucaio.gamedex.model.game.GameDetail
import br.com.seucaio.gamedex.repository.GamesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetGameDetailByIdUseCaseTest {

    private val repository: GamesRepository = mockk()
    private val useCase = GetGameDetailByIdUseCase(repository)

    @Test
    fun `should return result success with game detail when repository is successful`() =
        runTest {
            // Given
            val gameDetail = GameDetail(id = 1, name = "Game 1", description = "A great game")
            val successResult: Result<GameDetail> = Result.success(gameDetail)
            coEvery { repository.getById(1) } returns successResult

            // When
            val result: Result<GameDetail> = useCase(1)

            // Then
            assertTrue(result.isSuccess)
            assertEquals(successResult, result)
            assertEquals(gameDetail, result.getOrNull())
        }

    @Test
    fun `should return result failure when repository fails`() = runTest {
        // Given
        val exception = Exception()
        val failureResult: Result<GameDetail> = Result.failure(exception)
        coEvery { repository.getById(1) } returns failureResult

        // When
        val result: Result<GameDetail> = useCase(1)

        // Then
        assertTrue(result.isFailure)
        assertEquals(failureResult, result)
        assertEquals(result.exceptionOrNull(), exception)
    }
}
