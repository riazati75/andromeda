package ir.farsroidx.app.view

import android.content.Intent
import android.widget.Toast
import ir.farsroidx.app.databinding.ActivityMainBinding
import ir.farsroidx.m31.AndromedaActivity

class MainActivity : AndromedaActivity<ActivityMainBinding>() {

    override fun ActivityMainBinding.onInitialized() {

        app.setOnClickListener {
            startActivity<AppActivity>()
        }

        exception.setOnClickListener {
            startActivity<ExceptionActivity>()
        }

        dispatcher.setOnClickListener {
            Toast.makeText(baseContext, "View, not available", Toast.LENGTH_SHORT).show()
        }

        memory.setOnClickListener {
            startActivity<MemoryActivity>()
        }

        preference.setOnClickListener {
            startActivity<PreferenceActivity>()
        }

        cacheFile.setOnClickListener {
            startActivity<CacheActivity>()
        }

        database.setOnClickListener {
            startActivity<DatabaseActivity>()
        }

        network.setOnClickListener {
            startActivity<NetworkActivity>()
        }

        utils.setOnClickListener {
            startActivity<UtilsActivity>()
        }

        crypto.setOnClickListener {
            startActivity<CryptoActivity>()
        }
    }

    private inline fun <reified T> startActivity() = startActivity(
        Intent(this, T::class.java)
    )
}