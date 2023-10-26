package ir.farsroidx.app.view

import ir.farsroidx.app.databinding.ActivityCryptoBinding
import ir.farsroidx.m31.AndromedaActivity

class CryptoActivity : AndromedaActivity<ActivityCryptoBinding>() {

    override fun ActivityCryptoBinding.onInitialized() {

        arrowBack.setOnClickListener {
            onBackPressed()
        }
    }
}