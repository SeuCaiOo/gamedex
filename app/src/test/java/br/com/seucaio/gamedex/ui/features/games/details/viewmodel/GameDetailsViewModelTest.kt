package br.com.seucaio.gamedex.ui.features.games.details.viewmodel

import app.cash.turbine.test
import br.com.seucaio.gamedex.model.game.GameDetail
import br.com.seucaio.gamedex.usecase.game.GetGameDetailByIdUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GameDetailsViewModelTest {

    @MockK
    private lateinit var getGameDetailByIdUseCase: GetGameDetailByIdUseCase

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: GameDetailsViewModel

    private val testGameId = 10
    private val testGameDetail = mockk<GameDetail>(relaxed = true)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun initViewModel() {
        viewModel = GameDetailsViewModel(
            id = testGameId,
            getGameDetailByIdUseCase = getGameDetailByIdUseCase,
        )
    }

    // region UiState Tests

    @Test
    fun `should set state with game details when getGameDetailByIdUseCase succeeds`() = runTest {
        // Given
        coEvery { getGameDetailByIdUseCase(testGameId) } returns Result.success(testGameDetail)

        // When
        initViewModel()

        // Then
        viewModel.uiState.test {
            val initial = awaitItem()
            assertFalse(initial.isLoading)
            assertNull(initial.gameDetail)
            assertNull(initial.errorMessage)

            val loading = awaitItem()
            assertTrue(loading.isLoading)
            assertNull(loading.gameDetail)
            assertNull(loading.errorMessage)

            val success = awaitItem()
            assertFalse(success.isLoading)
            assertEquals(testGameDetail, success.gameDetail)
            assertNull(success.errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 1) { getGameDetailByIdUseCase(any()) }
    }

    @Test
    fun `should set state with error when getGameDetailByIdUseCase fails`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { getGameDetailByIdUseCase(testGameId) } returns Result.failure(
            Exception(
                errorMessage
            )
        )

        // When
        initViewModel()

        // Then
        viewModel.uiState.test {
            val initial = awaitItem()
            assertFalse(initial.isLoading)
            assertNull(initial.gameDetail)
            assertNull(initial.errorMessage)

            val loading = awaitItem()
            assertTrue(loading.isLoading)
            assertNull(loading.gameDetail)
            assertNull(loading.errorMessage)

            val error = awaitItem()
            assertFalse(error.isLoading)
            assertNull(error.gameDetail)
            assertEquals(errorMessage, error.errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 1) { getGameDetailByIdUseCase(any()) }
    }

    @Test
    fun `should call getGameDetailByIdUseCase again when RetryLoadGame action is triggered`() =
        runTest {
            // Given
            coEvery { getGameDetailByIdUseCase(testGameId) } returns Result.failure(Exception())

            // When
            initViewModel()

            // Then
            viewModel.uiState.test {
                // Skip default state
                skipItems(1)

                // Retry
                viewModel.handleUiAction(GameDetailsUiAction.RetryLoadGame)

                val loading = awaitItem()
                assertTrue(loading.isLoading)

                cancelAndIgnoreRemainingEvents()
            }
            coVerify(exactly = 2) { getGameDetailByIdUseCase(any()) }
        }
    // endregion

    // region UiEvent Tests
    @Test
    fun `should emit NavigateBack event when OnBackClick action is triggered`() = runTest {
        // Given
        coEvery { getGameDetailByIdUseCase(testGameId) } returns Result.success(testGameDetail)

        initViewModel()

        // When/Then
        viewModel.uiEvent.test {
            viewModel.handleUiAction(GameDetailsUiAction.OnBackClick)

            val event: GameDetailsUiEvent = awaitItem()
            assertTrue(event is GameDetailsUiEvent.NavigateBack)

            cancelAndIgnoreRemainingEvents()
        }
    }
    // endregion
}
