package br.com.seucaio.gamedex.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ConnectivityChecker(context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val isNetworkAvailable: Boolean
        get() = checkNetworkAvailable()

    private fun checkNetworkAvailable(): Boolean {
        val currentActiveNetwork = connectivityManager.activeNetwork ?: return false
        val currentNetworkCapabilities =
            connectivityManager.getNetworkCapabilities(currentActiveNetwork) ?: return false

        val transportTypes = listOf(
            NetworkCapabilities.TRANSPORT_WIFI,
            NetworkCapabilities.TRANSPORT_CELLULAR,
            NetworkCapabilities.TRANSPORT_ETHERNET,
            NetworkCapabilities.TRANSPORT_VPN
        )

        return transportTypes.any { currentNetworkCapabilities.hasTransport(it) }
    }

    suspend fun <T, R : Exception> executeNetworkRequest(
        onAction: suspend () -> T,
        onError: (throwable: Throwable) -> R,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): T {
        return withContext(dispatcher) {
            try {
                onAction.invoke()
            } catch (e: Exception) {
                throw onError.invoke(e)
            }
        }
    }
}