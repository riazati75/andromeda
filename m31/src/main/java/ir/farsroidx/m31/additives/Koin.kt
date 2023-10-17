@file:Suppress("REDUNDANT_ELSE_IN_WHEN", "unused")

package ir.farsroidx.m31.additives

import android.content.Context
import android.os.Debug
import com.google.gson.Gson
import ir.farsroidx.m31.AndromedaApplication
import ir.farsroidx.m31.AndromedaConfig
import ir.farsroidx.m31.AndromedaException
import ir.farsroidx.m31.AndromedaState
import ir.farsroidx.m31.app.App
import ir.farsroidx.m31.app.AppConfig
import ir.farsroidx.m31.app.AppImpl
import ir.farsroidx.m31.cache.Cache
import ir.farsroidx.m31.cache.CacheConfig
import ir.farsroidx.m31.cache.CacheImpl
import ir.farsroidx.m31.crypto.Crypto
import ir.farsroidx.m31.crypto.CryptoConfig
import ir.farsroidx.m31.crypto.CryptoImpl
import ir.farsroidx.m31.database.Database
import ir.farsroidx.m31.database.DatabaseConfig
import ir.farsroidx.m31.database.DatabaseImpl
import ir.farsroidx.m31.dispatcher.Dispatcher
import ir.farsroidx.m31.dispatcher.DispatcherConfig
import ir.farsroidx.m31.dispatcher.DispatcherImpl
import ir.farsroidx.m31.download.Download
import ir.farsroidx.m31.download.DownloadConfig
import ir.farsroidx.m31.download.DownloadImpl
import ir.farsroidx.m31.exception.ExceptionHandlerConfig
import ir.farsroidx.m31.exception.UncaughtExceptionHandlerImpl
import ir.farsroidx.m31.memory.Memory
import ir.farsroidx.m31.memory.MemoryConfig
import ir.farsroidx.m31.memory.MemoryImpl
import ir.farsroidx.m31.network.Network
import ir.farsroidx.m31.network.NetworkConfig
import ir.farsroidx.m31.network.NetworkImpl
import ir.farsroidx.m31.preference.Preference
import ir.farsroidx.m31.preference.PreferenceConfig
import ir.farsroidx.m31.preference.PreferenceImpl
import ir.farsroidx.m31.utils.Utils
import ir.farsroidx.m31.utils.UtilsConfig
import ir.farsroidx.m31.utils.UtilsImpl
import ir.farsroidx.m31.utils.common.CommonUtils
import ir.farsroidx.m31.utils.common.CommonUtilsImpl
import ir.farsroidx.m31.utils.time.TimeUtils
import ir.farsroidx.m31.utils.time.TimeUtilsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.Qualifier
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

// TODO: Koin ================================================================================== ///

internal inline fun <reified T> koinInjector(
    qualifier: Qualifier? = null,
): Lazy<T> {
    return lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        KoinJavaComponent.get(
            T::class.java, qualifier, null
        )
    }
}

internal fun initializedKoin(
    application: AndromedaApplication, configs: List<AndromedaConfig>
) = startKoin {
    androidLogger(Level.NONE)
    androidContext(application)
    mutableListOf(commonModule())
        .apply {
            configs.forEach { config ->
                when (config) {
                    is ExceptionHandlerConfig -> exceptionModule(application, config)
                    is AppConfig -> add(appModule())
                    is CacheConfig -> add(cacheModule(config))
                    is CryptoConfig -> add(cryptoModule(config))
                    is DatabaseConfig -> add(databaseModule(config))
                    is DispatcherConfig -> add(dispatcherModule())
                    is DownloadConfig -> add(downloadModule(config))
                    is MemoryConfig -> add(memoryModule(config))
                    is NetworkConfig -> add(networkModule(config))
                    is PreferenceConfig -> add(preferenceModule(config))
                    is UtilsConfig -> add(utilsModule(config))
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
    single { Gson() }
}

private fun exceptionModule(context: Context, config: ExceptionHandlerConfig) {
    if (!Debug.waitingForDebugger() && !Debug.isDebuggerConnected()) {
        Thread.setDefaultUncaughtExceptionHandler(
            UncaughtExceptionHandlerImpl(
                context, config
            )
        )
    }
}

private fun appModule() = module {
    single<App> {
        AppImpl(androidContext())
    }
    AndromedaState.isAppInitialized = true
}

private fun cacheModule(config: CacheConfig) = module {
    single<Cache> {
        CacheImpl(
            androidContext(), config
        )
    }
    AndromedaState.isCacheInitialized = true
}

private fun cryptoModule(config: CryptoConfig) = module {
    single<Crypto> {
        CryptoImpl(config)
    }
    AndromedaState.isCryptoInitialized = true
}

private fun databaseModule(config: DatabaseConfig) = module {
    single<Database> {
        DatabaseImpl(config)
    }
    AndromedaState.isDatabaseInitialized = true
}

private fun dispatcherModule() = module {
    single<Dispatcher> {
        DispatcherImpl()
    }
    AndromedaState.isDispatchersInitialized = true
}

private fun downloadModule(config: DownloadConfig) = module {
    single<Download> {
        DownloadImpl(androidContext(), config)
    }
    AndromedaState.isDownloadInitialized = true
}

private fun memoryModule(config: MemoryConfig) = module {
    single<Memory> {
        MemoryImpl(config)
    }
    AndromedaState.isMemoryInitialized = true
}

private fun networkModule(config: NetworkConfig) = module {
    single<Network> {
        NetworkImpl(
            androidContext(), config
        )
    }
    AndromedaState.isNetworkInitialized = true
}

private fun preferenceModule(config: PreferenceConfig) = module {
    single<Preference> {
        PreferenceImpl(
            androidContext(), get(), config
        )
    }
    AndromedaState.isPreferenceInitialized = true
}

private fun utilsModule(config: UtilsConfig) = module {
    single<CommonUtils> {
        CommonUtilsImpl(get())
    }
    single<TimeUtils> {
        TimeUtilsImpl()
    }
    single<Utils> {
        UtilsImpl(androidContext(), config, get(), get())
    }
    AndromedaState.isUtilsInitialized = true
}