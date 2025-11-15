package br.com.seucaio.gamedex.ui.features.platforms.list.viewmodel

import app.cash.turbine.test
import br.com.seucaio.gamedex.model.platform.GamePlatform
import br.com.seucaio.gamedex.usecase.platform.GetPlatformsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
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
class PlatformListViewModelTest {

    @MockK
    private lateinit var getPlatformsUseCase: GetPlatformsUseCase

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: PlatformListViewModel

    private val testPlatformList = listOf(
        GamePlatform(id = 1, name = "Platform 1", gamesCount = 10),
        GamePlatform(id = 2, name = "Platform 2", gamesCount = 20)
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
        viewModel = PlatformListViewModel(
            getPlatformsUseCase = getPlatformsUseCase,
        )
    }

    // region UiState Tests

    @Test
    fun `should set state with platform list when getPlatformsUseCase succeeds`() = runTest {
        // Given
        coEvery { getPlatformsUseCase() } returns Result.success(testPlatformList)

        // When
        initViewModel()

        // Then
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertFalse(initialState.isLoading)
            assertTrue(initialState.platforms.isNullOrEmpty())
            assertNull(initialState.errorMessage)

            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)
            assertTrue(loadingState.platforms.isNullOrEmpty())
            assertNull(loadingState.errorMessage)

            val successState = awaitItem()
            assertTrue(successState.platforms != null)
            assertFalse(successState.isLoading)
            assertEquals(testPlatformList, successState.platforms)
            assertNull(successState.errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 1) { getPlatformsUseCase() }
    }

    @Test
    fun `should set state with error when getPlatformsUseCase fails`() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { getPlatformsUseCase() } returns Result.failure(Exception(errorMessage))

        // When
        initViewModel()

        // Then
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertFalse(initialState.isLoading)
            assertTrue(initialState.platforms.isNullOrEmpty())
            assertNull(initialState.errorMessage)

            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)
            assertTrue(loadingState.platforms.isNullOrEmpty())
            assertNull(loadingState.errorMessage)

            val errorState = awaitItem()
            assertFalse(errorState.isLoading)
            assertTrue(errorState.platforms.isNullOrEmpty())
            assertEquals(errorMessage, errorState.errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 1) { getPlatformsUseCase() }
    }

    @Test
    fun `should call getPlatformsUseCase again when RetryLoadPlatforms action is triggered`() = runTest {
        // Given
        coEvery { getPlatformsUseCase() } returns Result.failure(Exception())

        // When
        initViewModel()

        // Then
        viewModel.uiState.test {
            // Skips initial, loading and error states from init
            skipItems(3)

            // Trigger retry
            viewModel.handleUiAction(PlatformListUiAction.RetryLoadPlatforms)

            // new loading and error states
            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)

            val errorState = awaitItem()
            assertTrue(errorState.errorMessage != null)

            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 2) { getPlatformsUseCase() }
    }
    // endregion

    // region UiEvent Tests
    @Test
    fun `should emit NavigateToDetail event when OnPlatformClick action is triggered`() = runTest {
        // Given
        coEvery { getPlatformsUseCase() } returns Result.success(testPlatformList)
        initViewModel()

        // When/Then
        viewModel.uiEvent.test {
            // Given
            val platformId = 1

            // When
            viewModel.handleUiAction(PlatformListUiAction.OnPlatformClick(platformId))

            // Then
            val event = awaitItem()
            assertTrue(event is PlatformListUiEvent.NavigateToDetail)
            assertEquals(platformId, event.platformId)

            cancelAndIgnoreRemainingEvents()
        }
    }
    // endregion
}
