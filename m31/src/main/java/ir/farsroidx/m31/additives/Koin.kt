@file:Suppress("REDUNDANT_ELSE_IN_WHEN", "unused")

package ir.farsroidx.m31.additives

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import ir.farsroidx.m31.AndromedaApplication
import ir.farsroidx.m31.AndromedaException
import ir.farsroidx.m31.AndromedaProvider
import ir.farsroidx.m31.app.App
import ir.farsroidx.m31.app.AppImpl
import ir.farsroidx.m31.cache.Cache
import ir.farsroidx.m31.cache.CacheImpl
import ir.farsroidx.m31.crypto.Crypto
import ir.farsroidx.m31.crypto.CryptoImpl
import ir.farsroidx.m31.database.Database
import ir.farsroidx.m31.database.DatabaseImpl
import ir.farsroidx.m31.download.Download
import ir.farsroidx.m31.download.DownloadImpl
import ir.farsroidx.m31.memory.Memory
import ir.farsroidx.m31.memory.MemoryImpl
import ir.farsroidx.m31.network.Network
import ir.farsroidx.m31.network.NetworkImpl
import ir.farsroidx.m31.preference.Preference
import ir.farsroidx.m31.preference.PreferenceImpl
import ir.farsroidx.m31.view.exception.AndromedaExceptionHandler
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

// TODO: Koin ============================================================================= Koin ===

internal inline fun <reified T> koinInjector(
    qualifier: Qualifier? = null,
): Lazy<T> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        KoinJavaComponent.get(
            T::class.java, qualifier, null
        )
    }
}

internal inline fun <reified T> isCasted(instance: Any?) = (instance is T)

internal fun AndromedaApplication.installAndromeda(providers: List<AndromedaProvider>) = startKoin {

    val application = this@installAndromeda

    androidLogger(Level.NONE)

    androidContext(application)

    mutableListOf(commonModule()).apply {

        providers.forEach { provider ->

            when (provider) {

                is AndromedaProvider.ExceptionUi -> exceptionModule(application, provider)
                is AndromedaProvider.Application -> add(applicationModule(        ))
                is AndromedaProvider.Cache       -> add(cacheModule      (provider))
                is AndromedaProvider.Crypto      -> add(cryptoModule     (provider))
                is AndromedaProvider.Database    -> add(databaseModule   (provider))
                is AndromedaProvider.Download    -> add(downloadModule   (provider))
                is AndromedaProvider.Memory      -> add(memoryModule     (provider))
                is AndromedaProvider.Network     -> add(networkModule    (provider))
                is AndromedaProvider.Preference  -> add(preferenceModule (provider))
                else -> {
                    throw AndromedaException(
                        "This type of AndromedaConfig is not supported, this config is invalid."
                    )
                }
            }
        }

        modules(this)
    }
}

private fun commonModule() = module {

    single {
        Gson()
    }

    single<Handler> {
        Handler(Looper.getMainLooper())
    }
}

private fun exceptionModule(
    application: AndromedaApplication, provider: AndromedaProvider.ExceptionUi
) {
    AndromedaExceptionHandler.install(application, provider.developerEmail)
}

private fun applicationModule() = module {
    single<App> {
        AppImpl(androidContext())
    }
}

private fun cacheModule(provider: AndromedaProvider.Cache) = module {
    single<Cache> {
        CacheImpl(
            androidContext(), provider
        )
    }
}

private fun cryptoModule(provider: AndromedaProvider.Crypto) = module {
    single<Crypto> {
        CryptoImpl(provider)
    }
}

private fun databaseModule(provider: AndromedaProvider.Database) = module {
    single<Database> {
        DatabaseImpl(provider)
    }
}

private fun downloadModule(provider: AndromedaProvider.Download) = module {
    single<Download> {
        DownloadImpl(androidContext(), provider)
    }
}

private fun memoryModule(provider: AndromedaProvider.Memory) = module {
    single<Memory> {
        MemoryImpl(provider)
    }
}

private fun networkModule(provider: AndromedaProvider.Network) = module {
    single<Network> {
        NetworkImpl(
            androidContext(), provider
        )
    }
}

private fun preferenceModule(provider: AndromedaProvider.Preference) = module {
    single<Preference> {
        PreferenceImpl(
            androidContext(), get(), provider
        )
    }
}