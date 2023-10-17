package ir.farsroidx.m31.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import ir.farsroidx.m31.utils.common.CommonUtils
import ir.farsroidx.m31.utils.time.TimeUtils

internal class UtilsImpl(
    private val mContext: Context,
    private val mConfig: UtilsConfig,
    private val mTimeUtils: TimeUtils,
    private val mCommonUtils: CommonUtils
) : Utils {

    //    override val timeUtils: TimeUtils = (mTimeUtils as TimeUtilsImpl).apply(mConfig.)

    //    override val commonUtils: CommonUtils = (mCommonUtils as CommonUtilsImpl).apply(mConfig.)

    override fun vibrateDevice(milliseconds: Long) {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = mContext.getSystemService(Context.VIBRATOR_MANAGER_SERVICE)
                as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            mContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
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