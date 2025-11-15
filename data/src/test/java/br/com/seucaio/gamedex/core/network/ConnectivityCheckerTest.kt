package br.com.seucaio.gamedex.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

@ExperimentalCoroutinesApi
class ConnectivityCheckerTest {

    @MockK
    private lateinit var context: Context

    @MockK(relaxed = true)
    private lateinit var connectivityManager: ConnectivityManager

    @MockK
    private lateinit var network: Network

    @MockK(relaxed = true)
    private lateinit var networkCapabilities: NetworkCapabilities

    private lateinit var connectivityChecker: ConnectivityChecker

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { context.getSystemService(Context.CONNECTIVITY_SERVICE) } returns connectivityManager
        connectivityChecker = ConnectivityChecker(context)
    }

    // region isNetworkAvailable Tests

    @Test
    fun `isNetworkAvailable should return false when activeNetwork is null`() {
        // Given
        every { connectivityManager.activeNetwork } returns null

        // When
        val result = connectivityChecker.isNetworkAvailable

        // Then
        assertFalse(result)
    }

    @Test
    fun `isNetworkAvailable should return false when networkCapabilities is null`() {
        // Given
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns null

        // When
        val result = connectivityChecker.isNetworkAvailable

        // Then
        assertFalse(result)
    }

    @Test
    fun `isNetworkAvailable should return true when has WIFI transport`() {
        // Given
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns networkCapabilities
        every { networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) } returns true

        // When
        val result = connectivityChecker.isNetworkAvailable

        // Then
        assertTrue(result)
    }

    @Test
    fun `isNetworkAvailable should return true when has CELLULAR transport`() {
        // Given
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns networkCapabilities
        every { networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) } returns true

        // When
        val result = connectivityChecker.isNetworkAvailable

        // Then
        assertTrue(result)
    }

    @Test
    fun `isNetworkAvailable should return true when has ETHERNET transport`() {
        // Given
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns networkCapabilities
        every { networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) } returns true

        // When
        val result = connectivityChecker.isNetworkAvailable

        // Then
        assertTrue(result)
    }

    @Test
    fun `isNetworkAvailable should return true when has VPN transport`() {
        // Given
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns networkCapabilities
        every { networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN) } returns true

        // When
        val result = connectivityChecker.isNetworkAvailable

        // Then
        assertTrue(result)
    }

    @Test
    fun `isNetworkAvailable should return false when has no valid transport`() {
        // Given
        every { connectivityManager.activeNetwork } returns network
        every { connectivityManager.getNetworkCapabilities(network) } returns networkCapabilities
        every { networkCapabilities.hasTransport(any()) } returns false

        // When
        val result = connectivityChecker.isNetworkAvailable

        // Then
        assertFalse(result)
    }

    // endregion

    // region executeNetworkRequest Tests

    @Test
    fun `executeNetworkRequest should return result on success`() = runTest {
        // Given
        val expectedResult = "Success"
        val onAction = suspend { expectedResult }
        val onError = { _: Throwable -> RuntimeException("Should not be called") }

        // When
        val result = connectivityChecker.executeNetworkRequest(onAction, onError)

        // Then
        assertEquals(expectedResult, result)
    }

    @Test
    fun `executeNetworkRequest should throw custom exception on error`() = runTest {
        // Given
        class CustomException(message: String) : Exception(message)

        val originalException = RuntimeException("Original Error")
        val onAction = suspend { throw originalException }
        val onError = { throwable: Throwable ->
            CustomException("Mapped: ${throwable.message}")
        }

        // When / Then
        val exception = assertFailsWith<CustomException> {
            connectivityChecker.executeNetworkRequest(onAction, onError)
        }
        assertEquals("Mapped: Original Error", exception.message)
    }

    // endregion
}