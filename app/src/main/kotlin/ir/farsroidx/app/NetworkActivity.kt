package ir.farsroidx.app

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ir.farsroidx.app.databinding.ActivityNetworkBinding
import ir.farsroidx.m31.Andromeda
import ir.farsroidx.m31.network.ConnectivityStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NetworkActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNetworkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNetworkBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }

        binding.arrowBack.setOnClickListener {
            onBackPressed()
        }

//        buildSpannedString {
//            inSpans(
//                ImageSpan(this@NetworkActivity, R.drawable.ic_launcher_background)
//            ) {
//
//            }
//        }

        CoroutineScope(Andromeda.dispatcher.io).launch {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Andromeda.network.statusObserver().onEach {
                    when (it) {
                        ConnectivityStatus.Available -> {
                            setStatusText("Available")
                        }

                        ConnectivityStatus.Losing -> {
                            setStatusText("Losing")
                        }

                        ConnectivityStatus.Lost -> {
                            setStatusText("Lost")
                        }

                        ConnectivityStatus.Unavailable -> {
                            setStatusText("Unavailable")
                        }
                    }
                }
            } else {
                setStatusText("Inaccessibility")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private suspend fun setStatusText(text: CharSequence) {
        withContext(Andromeda.dispatcher.main) {
            binding.connectivityTextView.text = "Connectivity Status: $text"
        }
    }
}