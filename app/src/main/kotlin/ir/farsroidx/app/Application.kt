package ir.farsroidx.app

import ir.farsroidx.m31.AndromedaApplication
import ir.farsroidx.m31.AndromedaProvider
import ir.farsroidx.m31.AndromedaTimeUnit

class Application : AndromedaApplication() {

    /**
     * If the settings for each part are not added,
     * you cannot use that part and you will encounter a crash!
     */
    override fun getAndromedaProviders(): List<AndromedaProvider> {

        return listOf(

            // TODO: if you want use of exception handler module ===================================
            AndromedaProvider.ExceptionUi(
                "mohammadali.riazati@yahoo.com"
            ),

            // TODO: if you want use of app module =================================================
            AndromedaProvider.Application,

            // TODO: if you want use of cache module ===============================================
            AndromedaProvider.Cache,

            // TODO: if you want use of crypto module ==============================================
            AndromedaProvider.Crypto,

            // TODO: if you want use of database module ============================================
            AndromedaProvider.Database,

            // TODO: if you want use of download module ============================================
            AndromedaProvider.Download,

            // TODO: if you want use of memory module ==============================================
            AndromedaProvider.Memory(
                5, AndromedaTimeUnit.Minutes
            ),

            // TODO: if you want use of network module =============================================
            AndromedaProvider.Network,

            // TODO: if you want use of preference module ==========================================
            AndromedaProvider.Preference(
                "farsroidx-preferences",
                1, AndromedaTimeUnit.Day
            ),

            // TODO: if you want use of utils module ===============================================
            AndromedaProvider.Utils,

        )
    }
}