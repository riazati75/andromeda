package ir.farsroidx.m31.utils

import android.Manifest
import androidx.annotation.RequiresPermission

interface Utils {

    @RequiresPermission(
        Manifest.permission.VIBRATE
    )
    fun vibrateDevice(milliseconds: Long)

//    val timeUtils: TimeUtils

//    val commonUtils: CommonUtils

}