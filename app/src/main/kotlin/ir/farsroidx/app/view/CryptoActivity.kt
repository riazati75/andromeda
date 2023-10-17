package ir.farsroidx.app.view

import ir.farsroidx.app.databinding.ActivityCryptoBinding
import ir.farsroidx.m31.AndromedaActivity

class CryptoActivity : AndromedaActivity<ActivityCryptoBinding>()  {

    override fun onInitialized() {

        binding {

            arrowBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}