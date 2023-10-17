package ir.farsroidx.app.view

import ir.farsroidx.app.databinding.ActivityCacheBinding
import ir.farsroidx.m31.AndromedaActivity

class CacheActivity : AndromedaActivity<ActivityCacheBinding>() {

    override fun onInitialized() {

        binding {

            arrowBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}