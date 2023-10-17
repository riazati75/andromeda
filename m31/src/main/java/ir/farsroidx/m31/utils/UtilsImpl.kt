package ir.farsroidx.m31.utils

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.annotation.RequiresPermission
import ir.farsroidx.m31.AndromedaProvider
import ir.farsroidx.m31.utils.common.CommonUtils
import ir.farsroidx.m31.utils.time.TimeUtils

internal class UtilsImpl(
    private val context: Context,
    private val provider: AndromedaProvider.Utils,
    private val timeUtils: TimeUtils,
    private val commonUtils: CommonUtils
) : Utils {

    //    override val timeUtils: TimeUtils = (timeUtils as TimeUtilsImpl).apply(provider.)

    //    override val commonUtils: CommonUtils = (commonUtils as CommonUtilsImpl).apply(provider.)

    @RequiresPermission(Manifest.permission.VIBRATE)
    override fun vibrateDevice(milliseconds: Long) {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE)
                as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                VibrationEffect.createOneShot(
                    milliseconds, VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            vibrator.vibrate(milliseconds)
        }
    }
}