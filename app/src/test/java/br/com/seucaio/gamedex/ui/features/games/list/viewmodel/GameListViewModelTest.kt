package br.com.seucaio.gamedex.ui.features.games.list.viewmodel

import app.cash.turbine.test
import br.com.seucaio.gamedex.model.game.GameItem
import br.com.seucaio.gamedex.usecase.game.GetGamesByPlatformByIdUseCase
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
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class GameListViewModelTest {

    @MockK
    private lateinit var getGamesByPlatformByIdUseCase: GetGamesByPlatformByIdUseCase

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: GameListViewModel

    private val testPlatformId = 3
    private val testQuery = ""
    private val testGameList = listOf(
        mockk<GameItem>(relaxed = true),
        mockk<GameItem>(relaxed = true)
    )

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
        viewModel = GameListViewModel(
            platformId = testPlatformId,
            gameQuery = testQuery,
            getGamesByPlatformByIdUseCase = getGamesByPlatformByIdUseCase,
        )
    }

    // region UiState Tests
    @Test
    fun `should set state with game list when getGamesByPlatformByIdUseCase succeeds`() = runTest {
        // Given
        coEvery { getGamesByPlatformByIdUseCase(testQuery, testPlatformId) } returns Result.success(
            testGameList
        )

        // When
        initViewModel()

        // Then
        viewModel.uiState.test {
            val initial = awaitItem()
            assertFalse(initial.isLoading)
            assertTrue(initial.gameItems.isNullOrEmpty())
            assertEquals(initial.errorMessage, null)

            val loading = awaitItem()
            assertTrue(loading.isLoading)
            assertTrue(loading.gameItems.isNullOrEmpty())
            assertEquals(loading.errorMessage, null)

            val success = awaitItem()
            assertFalse(success.isLoading)
            assertEquals(testGameList, success.gameItems)
            assertEquals(success.errorMessage, null)

            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 1) { getGamesByPlatformByIdUseCase(any(), any()) }
    }

    @Test
    fun `should set state with error when getGamesByPlatformByIdUseCase fails`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { getGamesByPlatformByIdUseCase(testQuery, testPlatformId) } returns Result.failure(
            Exception(errorMessage)
        )

        // When
        initViewModel()

        // Then
        viewModel.uiState.test {
            val initial = awaitItem()
            assertFalse(initial.isLoading)
            assertTrue(initial.gameItems.isNullOrEmpty())
            assertEquals(initial.errorMessage, null)

            val loading = awaitItem()
            assertTrue(loading.isLoading)
            assertTrue(loading.gameItems.isNullOrEmpty())
            assertEquals(loading.errorMessage, null)

            val error = awaitItem()
            assertFalse(error.isLoading)
            assertTrue(error.gameItems.isNullOrEmpty())
            assertEquals(errorMessage, error.errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 1) { getGamesByPlatformByIdUseCase(any(), any()) }
    }

    @Test
    fun `should call getGamesByPlatformByIdUseCase again when RetryLoadGames action is triggered`() =
        runTest {
            // Given
            coEvery {
                getGamesByPlatformByIdUseCase(
                    testQuery,
                    testPlatformId
                )
            } returns Result.failure(Exception())

            // When
            initViewModel()

            // Then
            viewModel.uiState.test {
                // Skips initial, loading and error states from init
                skipItems(3)

                // Trigger retry
                viewModel.handleUiAction(GameListUiAction.RetryLoadGames)

                val loading = awaitItem()
                assertTrue(loading.isLoading)

                val error = awaitItem()
                assertTrue(error.errorMessage != null)

                cancelAndIgnoreRemainingEvents()
            }
            coVerify(exactly = 2) { getGamesByPlatformByIdUseCase(any(), any()) }
        }
    // endregion

    // region UiEvent Tests
    @Test
    fun `should emit NavigateToDetail event when OnGameClick action is triggered`() = runTest {
        // Given
        coEvery {
            getGamesByPlatformByIdUseCase(testQuery, testPlatformId)
        } returns Result.success(testGameList)
        initViewModel()

        // When/Then
        viewModel.uiEvent.test {
            val gameId = 99
            viewModel.handleUiAction(GameListUiAction.OnGameClick(gameId))

            val event = awaitItem()
            assertTrue(event is GameListUiEvent.NavigateToDetail)
            assertEquals(gameId, event.platformId)

            cancelAndIgnoreRemainingEvents()
        }
    }
    // endregion
}
