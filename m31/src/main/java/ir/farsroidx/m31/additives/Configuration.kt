package ir.farsroidx.m31.additives

import android.Manifest
import androidx.annotation.RequiresPermission
import ir.farsroidx.m31.app.AppConfig
import ir.farsroidx.m31.cache.CacheConfig
import ir.farsroidx.m31.crypto.CryptoConfig
import ir.farsroidx.m31.database.DatabaseConfig
import ir.farsroidx.m31.dispatcher.DispatcherConfig
import ir.farsroidx.m31.download.DownloadConfig
import ir.farsroidx.m31.exception.ExceptionHandlerConfig
import ir.farsroidx.m31.memory.MemoryConfig
import ir.farsroidx.m31.network.NetworkConfig
import ir.farsroidx.m31.preference.PreferenceConfig
import ir.farsroidx.m31.utils.UtilsConfig

// TODO: Configuration ========================================================================= ///

fun configAppModule(): AppConfig {
    return AppConfig
}

fun configCacheModule(
    invoker: CacheConfig.() -> Unit = {}
): CacheConfig {
    return CacheConfig()
        .apply(invoker)
}

fun configCryptoModule(
    invoker: CryptoConfig.() -> Unit = {}
): CryptoConfig {
    return CryptoConfig()
        .apply(invoker)
}

fun configDatabaseModule(
    invoker: DatabaseConfig.() -> Unit = {}
): DatabaseConfig {
    return DatabaseConfig()
        .apply(invoker)
}

fun configDispatcherModule(): DispatcherConfig {
    return DispatcherConfig
}

@RequiresPermission(Manifest.permission.INTERNET)
fun configDownloadModule(
    invoker: DownloadConfig.() -> Unit = {}
): DownloadConfig {
    return DownloadConfig()
        .apply(invoker)
}

fun configExceptionHandlerModule(
    invoker: ExceptionHandlerConfig.() -> Unit = {}
): ExceptionHandlerConfig {
    return ExceptionHandlerConfig()
        .apply(invoker)
}

fun configMemoryModule(
    invoker: MemoryConfig.() -> Unit = {}
): MemoryConfig {
    return MemoryConfig()
        .apply(invoker)
}

@RequiresPermission(Manifest.permission.INTERNET)
fun configNetworkModule(
    baseUrl: String, invoker: NetworkConfig.() -> Unit = {}
): NetworkConfig {
    return NetworkConfig(baseUrl)
        .apply(invoker)
}

fun configPreferenceModule(
    invoker: PreferenceConfig.() -> Unit = {}
): PreferenceConfig {
    return PreferenceConfig()
        .apply(invoker)
}

@RequiresPermission(Manifest.permission.VIBRATE)
fun configUtilsModule(
    invoker: UtilsConfig.() -> Unit = {}
): UtilsConfig {
    return UtilsConfig()
        .apply(invoker)
}