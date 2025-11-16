package br.com.seucaio.gamedex.usecase.genre

import br.com.seucaio.gamedex.model.genre.GameGenre
import br.com.seucaio.gamedex.repository.GenresRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetGenresUseCaseTest {

    private val repository: GenresRepository = mockk()
    private val useCase = GetGenresUseCase(repository)

    @Test
    fun `should return result success with genres when repository is successful`() = runTest {
        // Given
        val genres: List<GameGenre> = listOf(
            GameGenre(id = 1, name = "Action", gamesCount = 100),
            GameGenre(id = 2, name = "Adventure", gamesCount = 200),
        )
        val successResult: Result<List<GameGenre>> = Result.success(genres)
        coEvery { repository.getAll() } returns successResult

        // When
        val result: Result<List<GameGenre>> = useCase()

        // Then
        assertTrue(result.isSuccess)
        assertEquals(successResult, result)
        assertEquals(genres, result.getOrNull())
    }

    @Test
    fun `should return result failure when repository fails`() = runTest {
        // Given
        val exception = Exception()
        val failureResult: Result<List<GameGenre>> = Result.failure(exception)
        coEvery { repository.getAll() } returns failureResult

        // When
        val result: Result<List<GameGenre>> = useCase()

        // Then
        assertTrue(result.isFailure)
        assertEquals(failureResult, result)
        assertEquals(result.exceptionOrNull(), exception)
    }

}