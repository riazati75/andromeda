package ir.farsroidx.app.core

import ir.farsroidx.m31.AndromedaApplication
import ir.farsroidx.m31.AndromedaConfig
import ir.farsroidx.m31.additives.configAppModule
import ir.farsroidx.m31.additives.configCacheModule
import ir.farsroidx.m31.additives.configCryptoModule
import ir.farsroidx.m31.additives.configDatabaseModule
import ir.farsroidx.m31.additives.configDispatcherModule
import ir.farsroidx.m31.additives.configDownloadModule
import ir.farsroidx.m31.additives.configExceptionHandlerModule
import ir.farsroidx.m31.additives.configMemoryModule
import ir.farsroidx.m31.additives.configNetworkModule
import ir.farsroidx.m31.additives.configPreferenceModule
import ir.farsroidx.m31.additives.configUtilsModule
import ir.farsroidx.m31.additives.utils.TimeUnits

class Application : AndromedaApplication() {

    /**
     * If the settings for each part are not added,
     * you cannot use that part and you will encounter a crash!
     */
    override val andromedaModules: List<AndromedaConfig> = listOf(

        // TODO: if you want use of app module
        configAppModule(),

        // TODO: if you want use of cache module
        configCacheModule {

        },

        // TODO: if you want use of crypto module
        configCryptoModule {

        },

        // TODO: if you want use of database module
        configDatabaseModule {

        },

        // TODO ( Recommended )
        // TODO: if you want use of dispatcher module
        configDispatcherModule(),

        // TODO: if you want use of download module
        configDownloadModule(),

        // TODO: if you want use of exception handler module
        configExceptionHandlerModule {
            setDeveloperEmail("mohammadali.riazati@yahoo.com")
        },

        // TODO: if you want use of memory module
        configMemoryModule {
            setExpirationTime(5, TimeUnits.Minutes)
        },

        // TODO: if you want use of network module
        configNetworkModule("") {

        },

        // TODO: if you want use of preference module
        configPreferenceModule {
            setPreferenceName("farsroidx-preferences")
            setExpirationTime(1, TimeUnits.Day)
        },

        // TODO: if you want use of utils module
        configUtilsModule {

        }
    )
}