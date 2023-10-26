package ir.farsroidx.app.view

import ir.farsroidx.app.databinding.ActivityUtilsBinding
import ir.farsroidx.m31.AndromedaActivity

class UtilsActivity : AndromedaActivity<ActivityUtilsBinding>() {

    override fun ActivityUtilsBinding.onInitialized() {

        arrowBack.setOnClickListener {
            onBackPressed()
        }
    }
}