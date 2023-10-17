package ir.farsroidx.app

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<CardView>(R.id.app).setOnClickListener {
            startActivity<AppActivity>()
        }

        findViewById<CardView>(R.id.exception).setOnClickListener {
            startActivity<ExceptionActivity>()
        }

        findViewById<CardView>(R.id.dispatcher).setOnClickListener {
            Toast.makeText(this, "View, not available", Toast.LENGTH_SHORT).show()
        }

        findViewById<CardView>(R.id.memory).setOnClickListener {
            startActivity<MemoryActivity>()
        }

        findViewById<CardView>(R.id.preference).setOnClickListener {
            startActivity<PreferenceActivity>()
        }

        findViewById<CardView>(R.id.cacheFile).setOnClickListener {
            startActivity<CacheActivity>()
        }

        findViewById<CardView>(R.id.database).setOnClickListener {
            startActivity<DatabaseActivity>()
        }

        findViewById<CardView>(R.id.network).setOnClickListener {
            startActivity<NetworkActivity>()
        }

        findViewById<CardView>(R.id.utils).setOnClickListener {
            startActivity<UtilsActivity>()
        }

        findViewById<CardView>(R.id.crypto).setOnClickListener {
            startActivity<CryptoActivity>()
        }
    }

    private inline fun <reified T> startActivity() = startActivity(
        Intent(this, T::class.java)
    )
}