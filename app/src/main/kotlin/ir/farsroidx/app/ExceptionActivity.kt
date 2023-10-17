package ir.farsroidx.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import ir.farsroidx.m31.AndromedaException

class ExceptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exception)

        findViewById<AppCompatImageView>(R.id.arrowBack)
            .setOnClickListener {
                onBackPressed()
            }

        findViewById<CardView>(R.id.killProcess).setOnClickListener {
            throw AndromedaException(
                "This error is issued only to test the software."
            )
        }
    }
}
