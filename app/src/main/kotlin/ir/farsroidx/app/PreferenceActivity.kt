package ir.farsroidx.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import ir.farsroidx.m31.Andromeda
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        findViewById<AppCompatImageView>(R.id.arrowBack)
            .setOnClickListener {
                onBackPressed()
            }

        CoroutineScope(Andromeda.dispatcher.io).launch {

            Andromeda.preference.store("Key0", "value0")
            Andromeda.preference.store("Key1", 1)
            Andromeda.preference.store("Key2", 2f)
            Andromeda.preference.store("Key3", 3.0)
            Andromeda.preference.store("Key4", 4L)
            Andromeda.preference.store("Key5", true)
            Andromeda.preference.store("Key6", mutableSetOf("2", "4", "6"))

            Log.d("CentralCore", "pref0: " + Andromeda.preference.get("Key0", "alternate"))
            Log.d("CentralCore", "pref1: " + Andromeda.preference.get("Key1", -1))
            Log.d("CentralCore", "pref2: " + Andromeda.preference.get("Key2", -1f))
            Log.d("CentralCore", "pref3: " + Andromeda.preference.get("Key3", -1.0))
            Log.d("CentralCore", "pref4: " + Andromeda.preference.get("Key4", -1L))
            Log.d("CentralCore", "pref5: " + Andromeda.preference.get("Key5", false))
            Log.d(
                "CentralCore",
                "pref6: " + Andromeda.preference.get("Key6", mutableSetOf<String>())
            )

            try {
                Andromeda.preference.store("Key7", mutableSetOf(5, 7, 9))
            } catch (e: Exception) {
                Log.d("CentralCore", "Exception (pref-store-Key7): " + e.message)
            }
        }
    }
}