package br.com.seucaio.gamedex.core.error

import br.com.seucaio.gamedex.core.error.NetworkErrorHandler.handleError
import br.com.seucaio.gamedex.util.exception.DomainException
import kotlinx.serialization.SerializationException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NetworkErrorHandlerTest {

    @Test
    fun `handleError should return NetworkUnavailableException when network is unavailable`() {
        // Given
        val error = RuntimeException()

        // When
        val result = error.handleError(isNetworkAvailable = false)

        // Then
        assertTrue(result is DomainException.NetworkUnavailableException)
    }

    @Test
    fun `handleError should return NoInternetException for UnknownHostException`() {
        // Given
        val error = Exception(UnknownHostException())

        // When
        val result = error.handleError(isNetworkAvailable = true)

        // Then
        assertTrue(result is DomainException.NoInternetException)
    }

    @Test
    fun `handleError should return NoInternetException for SocketException`() {
        // Given
        val error = Exception(SocketException())

        // When
        val result = error.handleError(isNetworkAvailable = true)

        // Then
        assertTrue(result is DomainException.NoInternetException)
    }

    @Test
    fun `handleError should return TimeoutException for SocketTimeoutException`() {
        // Given
        val error = Exception(SocketTimeoutException())

        // When
        val result = error.handleError(isNetworkAvailable = true)

        // Then
        assertTrue(result is DomainException.TimeoutException)
    }

    @Test
    fun `handleError should return TimeoutException for InterruptedIOException`() {
        // Given
        val error = Exception(InterruptedIOException())

        // When
        val result = error.handleError(isNetworkAvailable = true)

        // Then
        assertTrue(result is DomainException.TimeoutException)
    }

    @Test
    fun `handleError should return ServerException for HttpException with code 500`() {
        // Given
        val httpException = HttpException(
            Response.error<Any>(500, "".toResponseBody("application/json".toMediaType()))
        )
        val error = Exception(httpException)

        // When
        val result = error.handleError(isNetworkAvailable = true)

        // Then
        assertTrue(result is DomainException.ServerException)
    }

    @Test
    fun `handleError should return ApiException for HttpException with code 404`() {
        // Given
        val httpException = HttpException(
            Response.error<Any>(404, "".toResponseBody("application/json".toMediaType()))
        )
        val error = Exception(httpException)

        // When
        val result = error.handleError(isNetworkAvailable = true)

        // Then
        assertTrue(result is DomainException.ApiException)
    }

    @Test
    fun `handleError should return ParseException for SerializationException`() {
        // Given
        val error = Exception(SerializationException())

        // When
        val result = error.handleError(isNetworkAvailable = true)

        // Then
        assertTrue(result is DomainException.ParseException)
    }

    @Test
    fun `handleError should return UnknownException for other exceptions`() {
        // Given
        val error = Exception(IllegalArgumentException())

        // When
        val result = error.handleError(isNetworkAvailable = true)

        // Then
        assertTrue(result is DomainException.UnknownException)
    }

    @Test
    fun `handleError should return UnknownException for null cause`() {
        // Given
        val error = Exception() // Sem causa

        // When
        val result = error.handleError(isNetworkAvailable = true)

        // Then
        assertTrue(result is DomainException.UnknownException)
    }
}
