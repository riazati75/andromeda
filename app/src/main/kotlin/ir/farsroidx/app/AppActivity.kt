package ir.farsroidx.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView

class AppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        findViewById<AppCompatImageView>(R.id.arrowBack)
            .setOnClickListener {
                onBackPressed()
            }
    }
}