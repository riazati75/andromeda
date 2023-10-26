@file:Suppress("unused", "UnusedReceiverParameter")

package ir.farsroidx.m31.additives

import ir.farsroidx.m31.AndromedaTimeUnit
import kotlin.system.exitProcess

// TODO: Utils =========================================================================== Utils ===

internal fun Int?.toExpirationTime(unit: AndromedaTimeUnit?): Long? {
    if (this == null || unit == null) return null
    val seconds = this * unit.value
    return (System.currentTimeMillis() / 1_000L) + seconds
}

internal fun Long?.isExpired(): Boolean {
    if (this == null) return false
    return (System.currentTimeMillis() / 1_000L) > this
}

internal fun killApplication() {
    android.os.Process.killProcess(
        android.os.Process.myPid()
    )
    exitProcess(10)
}