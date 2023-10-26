package ir.farsroidx.app.view

import ir.farsroidx.app.databinding.ActivityExceptionBinding
import ir.farsroidx.m31.AndromedaActivity

class ExceptionActivity : AndromedaActivity<ActivityExceptionBinding>() {

    override fun ActivityExceptionBinding.onInitialized() {

        arrowBack.setOnClickListener {
            onBackPressed()
        }

        killProcess.setOnClickListener {
            throw RuntimeException(
                "This error is issued only to test the software."
            )
        }
    }
}
