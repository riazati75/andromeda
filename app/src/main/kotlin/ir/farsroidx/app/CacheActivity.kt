package ir.farsroidx.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView

class CacheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache)

        findViewById<AppCompatImageView>(R.id.arrowBack)
            .setOnClickListener {
                onBackPressed()
            }

    }
}