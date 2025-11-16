package br.com.seucaio.gamedex.core.error

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import br.com.seucaio.gamedex.core.network.ConnectivityChecker
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

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

}