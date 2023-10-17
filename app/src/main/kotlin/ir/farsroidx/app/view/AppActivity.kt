package ir.farsroidx.app.view

import ir.farsroidx.app.databinding.ActivityAppBinding
import ir.farsroidx.m31.AndromedaActivity

class AppActivity : AndromedaActivity<ActivityAppBinding>() {

    override fun onInitialized() {

        binding {

            arrowBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}