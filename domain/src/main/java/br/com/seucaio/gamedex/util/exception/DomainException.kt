package br.com.seucaio.gamedex.util.exception

sealed class DomainException(
    message: String? = null,
    cause: Throwable? = null
) : Exception(message, cause) {
    data class NetworkUnavailableException(
        val throwable: Throwable? = null
    ) : DomainException(message = "Network unavailable", cause = throwable)

    data class NoInternetException(
        val throwable: Throwable? = null
    ) : DomainException(message = "No internet connection", cause = throwable)

    data class TimeoutException(
        val throwable: Throwable? = null
    ) : DomainException(message = "Request timeout", cause = throwable)

    data class ServerException(
        val throwable: Throwable? = null
    ) : DomainException(message = "Server error", cause = throwable)

    data class ApiException(
        val code: Int,
        val throwable: Throwable? = null
    ) : DomainException(message = "API error: $code", cause = throwable)

    data class ParseException(
        val throwable: Throwable? = null
    ) : DomainException(message = "Parse error", cause = throwable)

    data class UnknownException(
        val throwable: Throwable? = null
    ) : DomainException(message = "Unknown error", cause = throwable)
}
