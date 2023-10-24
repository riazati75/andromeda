package ir.farsroidx.m31.network

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.plugin
import io.ktor.client.plugins.timeout
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import ir.farsroidx.m31.AndromedaProvider
import ir.farsroidx.m31.additives.dLog
import ir.farsroidx.m31.additives.networkCallback
import ir.farsroidx.m31.additives.removeWhitespace
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

internal class NetworkImpl(
    context: Context, private val provider: AndromedaProvider.Network
) : Network {

    private val connectivityManager = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

    @RequiresApi(Build.VERSION_CODES.N)
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun observeNetworkStatus(): Flow<ConnectivityStatus> {
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

    init {

        _client = HttpClient(OkHttp) {

            engine {

                addInterceptor {
                    it.proceed(it.request())
                }

                config {
                    connectTimeout(provider.timeOut, TimeUnit.SECONDS)
                    callTimeout(provider.timeOut, TimeUnit.SECONDS)
                    readTimeout(provider.timeOut, TimeUnit.SECONDS)
                    writeTimeout(provider.timeOut, TimeUnit.SECONDS)
                }
            }

//            install(JsonFeature) {
//
//                serializer = KotlinxSerializer(Json {
//                    prettyPrint = true
//                    isLenient = true
//                    ignoreUnknownKeys = true
//                })
//
//                engine {
//                    connectTimeout = 10_000
//                    socketTimeout = 10_000
//                }
//            }

            install(ResponseObserver) {

                onResponse { response ->
                    dLog(
                        "HTTP Status: ${response.status.value} ${response.status.description}"
                    )
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

        }.apply {

            plugin(HttpSend).intercept { request ->

                val originalCall = execute(request)

                if (originalCall.response.status.value !in 100..399) {
                    execute(request)
                } else {
                    originalCall
                }
            }
        }
    }

//    suspend fun getUserKtor(
//        userId: String
//    ): UserEntity = client.get("$END_POINT_GET_USER_KTOR$userId")

//    suspend fun saveUser(user: UserEntity) {
//        client.post<UserEntity>("$END_POINT_POST_USER_KTOR") {
//            body = user
//        }
//    }

//    fun build() {
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
//    }

    override suspend fun get(): String {
        val response = client.get(provider.baseUrl + "todos/1") {
            timeout {
                connectTimeoutMillis = provider.timeOut
                requestTimeoutMillis = provider.timeOut
                socketTimeoutMillis  = provider.timeOut
            }
        }
        return response.bodyAsText().removeWhitespace()
    }

    override suspend fun post(): String {
        TODO("Not yet implemented")
    }
}