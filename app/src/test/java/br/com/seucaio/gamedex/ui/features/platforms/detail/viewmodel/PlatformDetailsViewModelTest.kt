package br.com.seucaio.gamedex.ui.features.platforms.detail.viewmodel

import app.cash.turbine.test
import br.com.seucaio.gamedex.model.platform.GamePlatformDetail
import br.com.seucaio.gamedex.usecase.platform.GetPlatformDetailByIdUseCase
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
class PlatformDetailsViewModelTest {

    @MockK
    private lateinit var getPlatformByIdUseCase: GetPlatformDetailByIdUseCase

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: PlatformDetailsViewModel

    private val testPlatformId = 1
    private val testPlatform = GamePlatformDetail(
        id = testPlatformId,
        name = "PlayStation 5",
        gamesCount = 1500,
        description = "Next-gen console",
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
        viewModel = PlatformDetailsViewModel(
            id = testPlatformId,
            getPlatformByIdUseCase = getPlatformByIdUseCase,
        )
    }

    // region UiState Tests

    @Test
    fun `should set state with platform details when getPlatformByIdUseCase succeeds`() = runTest {
        // Given
        coEvery { getPlatformByIdUseCase(testPlatformId) } returns Result.success(testPlatform)

        // When
        initViewModel()

        // Then
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertFalse(initialState.isLoading)
            assertNull(initialState.platformDetail)
            assertNull(initialState.errorMessage)

            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)
            assertNull(loadingState.platformDetail)
            assertNull(loadingState.errorMessage)

            val successState = awaitItem()
            assertTrue(successState.platformDetail != null)
            assertFalse(successState.isLoading)
            assertEquals(testPlatform, successState.platformDetail)
            assertNull(successState.errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 1) { getPlatformByIdUseCase(any()) }
    }

    @Test
    fun `should set state with error when getPlatformByIdUseCase fails `() = runTest {
        // Given
        val errorMessage = "Network error"
        coEvery { getPlatformByIdUseCase(testPlatformId) } returns Result.failure(
            Exception(errorMessage)
        )

        // When
        initViewModel()

        // Then
        viewModel.uiState.test {
            val initialState = awaitItem()
            assertFalse(initialState.isLoading)
            assertNull(initialState.platformDetail)
            assertNull(initialState.errorMessage)

            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)
            assertNull(loadingState.platformDetail)
            assertNull(loadingState.errorMessage)

            val errorState = awaitItem()
            assertTrue(!errorState.errorMessage.isNullOrBlank())
            assertFalse(errorState.isLoading)
            assertNull(errorState.platformDetail)
            assertEquals(errorMessage, errorState.errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
        coVerify(exactly = 1) { getPlatformByIdUseCase(any()) }
    }

    @Test
    fun `should call getPlatformByIdUseCase again when RetryLoadPlatforms action is triggered`() =
        runTest {
            // Given
            coEvery { getPlatformByIdUseCase(testPlatformId) } returns Result.failure(Exception())

            // When
            initViewModel()

            // Then
            viewModel.uiState.test {
                // Skip default
                skipItems(1)

                // Retry
                viewModel.handleUiAction(PlatformDetailsUiAction.RetryLoadPlatform)

                val loadingState = awaitItem()
                assertTrue(loadingState.isLoading)

                cancelAndIgnoreRemainingEvents()
            }
            coVerify(exactly = 2) { getPlatformByIdUseCase(any()) }
        }
    // endregion

    // region UiEvent Tests

    @Test
    fun `should emit NavigateBack event when OnBackClick action is triggered`() = runTest {
        // Given
        coEvery { getPlatformByIdUseCase(testPlatformId) } returns Result.success(testPlatform)

        initViewModel()

        // When/Then
        viewModel.uiEvent.test {
            viewModel.handleUiAction(PlatformDetailsUiAction.OnBackClick)

            val event: PlatformDetailsUiEvent = awaitItem()
            assertTrue(event is PlatformDetailsUiEvent.NavigateBack)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should emit NavigateToDetail event when OnGameClick action is triggered`() = runTest {
        // Given
        coEvery { getPlatformByIdUseCase(testPlatformId) } returns Result.success(testPlatform)

        initViewModel()

        // When/Then
        viewModel.uiEvent.test {
            val gameId = 99
            viewModel.handleUiAction(PlatformDetailsUiAction.OnGameClick(gameId))

            val event = awaitItem()
            assertTrue(event is PlatformDetailsUiEvent.NavigateToGameDetails)
            assertEquals(gameId, event.gameId)

            cancelAndIgnoreRemainingEvents()
        }
    }

    // endregion

    // region Search feature tests

    @Test
    fun `should show and hide search sheet when respective actions are triggered`() = runTest {
        // Given
        coEvery { getPlatformByIdUseCase(testPlatformId) } returns Result.success(testPlatform)

        // When
        initViewModel()

        // Then
        viewModel.uiState.test {
            // initial, loading, success from init
            skipItems(3)

            // Open search sheet
            viewModel.handleUiAction(PlatformDetailsUiAction.OpenSearchSheet)
            val openState = awaitItem()
            assertTrue(openState.isSearchSheetVisible)

            // Dismiss search sheet
            viewModel.handleUiAction(PlatformDetailsUiAction.DismissSearchSheet)
            val dismissState = awaitItem()
            assertFalse(dismissState.isSearchSheetVisible)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should update search query on OnSearchQueryChange action`() = runTest {
        // Given
        coEvery { getPlatformByIdUseCase(testPlatformId) } returns Result.success(testPlatform)

        initViewModel()

        viewModel.uiState.test {
            // initial, loading, success from init
            skipItems(3)

            val query = "zelda"
            viewModel.handleUiAction(PlatformDetailsUiAction.OnSearchQueryChange(query))

            val updatedState = awaitItem()
            assertEquals(query, updatedState.searchQuery)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should not emit event when OnSearchClick with blank query`() = runTest {
        // Given
        coEvery { getPlatformByIdUseCase(testPlatformId) } returns Result.success(testPlatform)

        initViewModel()

        // Open sheet to ensure visibility prior to click
        viewModel.handleUiAction(PlatformDetailsUiAction.OpenSearchSheet)

        viewModel.uiEvent.test {
            // When query is blank by default, clicking search should do nothing
            viewModel.handleUiAction(PlatformDetailsUiAction.OnSearchClick)

            // Then
            expectNoEvents()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should emit NavigateToGamesBySearch and hide sheet on OnSearchClick with non blank query`() = runTest {
        // Given
        coEvery { getPlatformByIdUseCase(testPlatformId) } returns Result.success(testPlatform)

        initViewModel()

        val query = "mario"

        // Prepare state: set query and open sheet
        viewModel.handleUiAction(PlatformDetailsUiAction.OnSearchQueryChange(query))
//        viewModel.handleUiAction(PlatformDetailsUiAction.OpenSearchSheet)

        // Assert event emission
        viewModel.uiEvent.test {
            viewModel.handleUiAction(PlatformDetailsUiAction.OnSearchClick)

            val event = awaitItem()
            assertTrue(event is PlatformDetailsUiEvent.NavigateToGamesBySearch)
            assertEquals(query, event.query)

            cancelAndIgnoreRemainingEvents()
        }
    }

    // endregion
}
