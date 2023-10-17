package ir.farsroidx.m31.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import io.ktor.client.HttpClient
import ir.farsroidx.m31.AndromedaProvider
import ir.farsroidx.m31.additives.networkCallback
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

internal class NetworkImpl(
    context: Context, private val provider: AndromedaProvider.Network
) : Network {

    private val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    @SuppressLint("MissingPermission", "NewApi")
    override fun statusObserver(): Flow<ConnectivityStatus> {
        return callbackFlow {
            val callback = networkCallback {
                onAvailable { launch { send(ConnectivityStatus.Available) } }
                onLosing { launch { send(ConnectivityStatus.Losing) } }
                onLost { launch { send(ConnectivityStatus.Lost) } }
                onUnavailable { launch { send(ConnectivityStatus.Unavailable) } }
            }
            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose { connectivityManager.unregisterNetworkCallback(callback) }
        }.distinctUntilChanged()
    }

    private var _client: HttpClient? = null

    val client: HttpClient by lazy { _client!! }

    fun build() {
//        _client = HttpClient(OkHttp) {
//            engine {
//                // this: OkHttpConfig
//                config {
//                    // this: OkHttpClient.Builder
//                    followRedirects(true)
//                    // ...
//                }
//                addInterceptor(interceptor)
//                addNetworkInterceptor(interceptor)
//
//                preconfigured = okHttpClientInstance
//            }
//        }
    }
}

//fun networkProvider(
//    initializer: NetworkProvider.() -> Unit
//) {
////    return NetworkProvider.apply(
////        initializer
////    ).build()
//    return NetworkProvider.apply(initializer)
//}

