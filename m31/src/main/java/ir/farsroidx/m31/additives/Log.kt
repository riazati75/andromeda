@file:Suppress("unused")

package ir.farsroidx.m31.additives

import android.util.Log
import ir.farsroidx.m31.AndromedaConstants

// TODO: Log =============================================================================== Log ===

fun vLog(vararg logs: Any?) {
    logs.forEach { log ->
        if ( log != null ) {
            Log.v(AndromedaConstants.LOG_TAG, log.toString())
        } else {
            Log.v(AndromedaConstants.LOG_TAG, "Log is null!")
        }
    }
}

fun iLog(vararg logs: Any?) {
    logs.forEach { log ->
        if ( log != null ) {
            Log.i(AndromedaConstants.LOG_TAG, log.toString())
        } else {
            Log.i(AndromedaConstants.LOG_TAG, "Log is null!")
        }
    }
}

fun dLog(vararg logs: Any?) {
    logs.forEach { log ->
        if ( log != null ) {
            Log.d(AndromedaConstants.LOG_TAG, log.toString())
        } else {
            Log.d(AndromedaConstants.LOG_TAG, "Log is null!")
        }
    }
}

fun wLog(vararg logs: Any?) {
    logs.forEach { log ->
        if ( log != null ) {
            Log.w(AndromedaConstants.LOG_TAG, log.toString())
        } else {
            Log.w(AndromedaConstants.LOG_TAG, "Log is null!")
        }
    }
}

fun eLog(vararg logs: Any?) {
    logs.forEach { log ->
        if ( log != null ) {
            Log.e(AndromedaConstants.LOG_TAG, log.toString())
        } else {
            Log.e(AndromedaConstants.LOG_TAG, "Log is null!")
        }
    }
}
