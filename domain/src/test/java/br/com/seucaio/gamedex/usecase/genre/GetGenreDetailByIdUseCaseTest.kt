package br.com.seucaio.gamedex.usecase.genre

import br.com.seucaio.gamedex.model.genre.GameGenreDetail
import br.com.seucaio.gamedex.repository.GenresRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetGenreDetailByIdUseCaseTest {

    private val repository: GenresRepository = mockk()
    private val useCase = GetGenreDetailByIdUseCase(repository)

    @Test
    fun `should return result success with genre detail when repository is successful`() =
        runTest {
            // Given
            val genreDetail =
                GameGenreDetail(id = 1, name = "Action", description = "...", gamesCount = 100)
            val successResult: Result<GameGenreDetail> = Result.success(genreDetail)
            coEvery { repository.getById(1) } returns successResult

            // When
            val result: Result<GameGenreDetail> = useCase(1)

            // Then
            assertTrue(result.isSuccess)
            assertEquals(successResult, result)
            assertEquals(genreDetail, result.getOrNull())
        }

    @Test
    fun `should return result failure when repository fails`() = runTest {
        // Given
        val exception = Exception()
        val failureResult: Result<GameGenreDetail> = Result.failure(exception)
        coEvery { repository.getById(1) } returns failureResult

        // When
        val result: Result<GameGenreDetail> = useCase(1)

        // Then
        assertTrue(result.isFailure)
        assertEquals(failureResult, result)
        assertEquals(result.exceptionOrNull(), exception)
    }
}
