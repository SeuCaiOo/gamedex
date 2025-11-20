package br.com.seucaio.gamedex.usecase.game

import br.com.seucaio.gamedex.model.game.GameItem
import br.com.seucaio.gamedex.repository.GamesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetGamesByPlatformByIdUseCaseTest {

    private val repository: GamesRepository = mockk()
    private val useCase = GetGamesByPlatformByIdUseCase(repository)

    @Test
    fun `should return result success with games when repository is successful`() = runTest {
        // Given
        val games: List<GameItem> = listOf(
            GameItem(id = 1, name = "Game 1", imageBackground = ""),
            GameItem(id = 2, name = "Game 2", imageBackground = ""),
        )
        val successResult: Result<List<GameItem>> = Result.success(games)
        coEvery { repository.searchByPlatform("test", 1) } returns successResult

        // When
        val result: Result<List<GameItem>> = useCase(query = "test", platformId = 1)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(successResult, result)
        assertEquals(games, result.getOrNull())
    }

    @Test
    fun `should return result failure when repository fails`() = runTest {
        // Given
        val exception = Exception()
        val failureResult: Result<List<GameItem>> = Result.failure(exception)
        coEvery { repository.searchByPlatform("test", 1) } returns failureResult

        // When
        val result: Result<List<GameItem>> = useCase(query = "test", platformId = 1)

        // Then
        assertTrue(result.isFailure)
        assertEquals(failureResult, result)
        assertEquals(result.exceptionOrNull(), exception)
    }
}
