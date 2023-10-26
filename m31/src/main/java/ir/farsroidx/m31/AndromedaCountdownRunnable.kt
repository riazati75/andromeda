@file:Suppress("unused", "ProtectedInFinal")

package ir.farsroidx.m31

import android.os.Handler
import ir.farsroidx.m31.additives.koinInjector

class AndromedaCountdownRunnable(private val initValue: Int) : Runnable {

    protected val handler: Handler by koinInjector()

    private var callback: (String, Boolean) -> Unit = { _, _ -> }

    private var time = 0

    init { time = initValue }

    override fun run() {

        val formatted = "${
            (time / 60).toString().padStart(2, '0')
        } : ${
            (time % 60).toString().padStart(2, '0')
        }"

        // TODO: Formatted: 01:21 ==================================================================

        if (time > 0) {

            callback( formatted , false )

            handler.postDelayed(this, 1_000)

        } else {

            callback( formatted , true )

        }

        time -= 1
    }

    fun reset() {
        time = initValue
    }

    fun stop() {
        handler.removeCallbacks(this)
    }

    fun setCallback(callback: (String, Boolean) -> Unit) {
        this.callback = callback
    }
}