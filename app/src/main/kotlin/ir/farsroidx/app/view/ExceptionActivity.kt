package ir.farsroidx.app.view

import ir.farsroidx.app.databinding.ActivityExceptionBinding
import ir.farsroidx.m31.AndromedaActivity
import ir.farsroidx.m31.AndromedaException

class ExceptionActivity : AndromedaActivity<ActivityExceptionBinding>() {

    override fun onInitialized() {

        binding {

            arrowBack.setOnClickListener {
                onBackPressed()
            }

            killProcess.setOnClickListener {
                throw AndromedaException(
                    "This error is issued only to test the software."
                )
            }
        }
    }
}
