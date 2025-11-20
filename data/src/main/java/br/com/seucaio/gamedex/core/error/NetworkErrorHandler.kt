package br.com.seucaio.gamedex.core.error

import br.com.seucaio.gamedex.util.exception.DomainException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.cancellation.CancellationException

private const val SERVER_ERROR_500 = 500
private const val SERVER_ERROR_599 = 599

object NetworkErrorHandler {
    fun Throwable.handleNetworkError(isNetworkAvailable: Boolean): DomainException {
        if (!isNetworkAvailable) return DomainException.NetworkUnavailableException(cause)
        return when (val throwable = cause) {
            is UnknownHostException,
            is SocketException -> DomainException.NoInternetException(throwable)

            is SocketTimeoutException,
            is InterruptedIOException -> DomainException.TimeoutException(throwable)

            is HttpException -> {
                val serverErrorRange = SERVER_ERROR_500..SERVER_ERROR_599
                when (throwable.code()) {
                    in serverErrorRange -> DomainException.ServerException(throwable)
                    else -> DomainException.ApiException(throwable.code(), throwable)
                }
            }

            is SerializationException -> DomainException.ParseException(throwable)
            else -> DomainException.UnknownException(throwable)
        }
    }

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        onNetworkAvailable: () -> Boolean,
        block: suspend () -> T
    ): T {
        return withContext(dispatcher) {
            runCatching { block() }.getOrElse { e ->
                if (e is CancellationException) throw e
                throw e.handleNetworkError(onNetworkAvailable.invoke())
            }
        }
    }
}
