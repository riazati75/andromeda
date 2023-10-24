package ir.farsroidx.m31

import android.app.Application
import android.content.Context
import androidx.annotation.CallSuper
import androidx.multidex.MultiDex
import ir.farsroidx.m31.additives.installAndromeda

abstract class AndromedaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        installAndromeda( getAndromedaProviders() )

    }

    @CallSuper
    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

    abstract fun getAndromedaProviders(): List<AndromedaProvider>
}