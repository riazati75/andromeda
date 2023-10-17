package ir.farsroidx.m31

import android.app.Application
import android.content.Context
import androidx.annotation.CallSuper
import androidx.multidex.MultiDex
import ir.farsroidx.m31.additives.install

abstract class AndromedaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Andromeda
        Andromeda.install(this, getAndromedaProviders())
    }

    @CallSuper
    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

    abstract fun getAndromedaProviders(): List<AndromedaProvider>
}