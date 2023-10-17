package ir.farsroidx.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView

class DatabaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        findViewById<AppCompatImageView>(R.id.arrowBack)
            .setOnClickListener {
                onBackPressed()
            }

    }
}