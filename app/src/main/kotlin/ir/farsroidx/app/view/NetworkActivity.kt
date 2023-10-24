package ir.farsroidx.app.view

import android.annotation.SuppressLint
import android.os.Build
import ir.farsroidx.app.databinding.ActivityNetworkBinding
import ir.farsroidx.m31.Andromeda
import ir.farsroidx.m31.AndromedaActivity
import ir.farsroidx.m31.network.ConnectivityStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class NetworkActivity : AndromedaActivity<ActivityNetworkBinding>() {

    override fun onInitialized() {

        binding {

            arrowBack.setOnClickListener {
                onBackPressed()
            }

            runBlocking(Andromeda.dispatcher.io) {
                val text = Andromeda.network.get()
                txtResponse.text = text
            }

            CoroutineScope(Andromeda.dispatcher.io).launch {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    Andromeda.network.observeNetworkStatus().onEach {

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
    }

    @SuppressLint("SetTextI18n")
    private suspend fun setStatusText(text: CharSequence) {
        withContext(Andromeda.dispatcher.main) {
            binding.connectivityTextView.text = "Connectivity Status: $text"
        }
    }
}