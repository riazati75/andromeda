package ir.farsroidx.app.view

import ir.farsroidx.app.databinding.ActivityDatabaseBinding
import ir.farsroidx.m31.AndromedaActivity

class DatabaseActivity : AndromedaActivity<ActivityDatabaseBinding>() {

    override fun ActivityDatabaseBinding.onInitialized() {

        arrowBack.setOnClickListener {
            onBackPressed()
        }
    }
}