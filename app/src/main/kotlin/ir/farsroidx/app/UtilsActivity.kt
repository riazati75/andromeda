package ir.farsroidx.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView

class UtilsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utils)

        findViewById<AppCompatImageView>(R.id.arrowBack)
            .setOnClickListener {
                onBackPressed()
            }
    }
}