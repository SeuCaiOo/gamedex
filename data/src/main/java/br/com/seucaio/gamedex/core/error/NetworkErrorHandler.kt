package br.com.seucaio.gamedex.core.error

import br.com.seucaio.gamedex.util.exception.DomainException
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object NetworkErrorHandler {
    fun Throwable.handleNetworkError(isNetworkAvailable: Boolean): DomainException {
        if (!isNetworkAvailable) return DomainException.NetworkUnavailableException(cause)
        return when (val throwable = cause) {
            is UnknownHostException,
            is SocketException -> DomainException.NoInternetException(throwable)

            is SocketTimeoutException,
            is InterruptedIOException -> DomainException.TimeoutException(throwable)

            is HttpException -> when (throwable.code()) {
                in 500..599 -> DomainException.ServerException(throwable)
                else -> DomainException.ApiException(throwable.code(), throwable)
            }

            is SerializationException -> DomainException.ParseException(throwable)
            else -> DomainException.UnknownException(throwable)
        }
    }
}