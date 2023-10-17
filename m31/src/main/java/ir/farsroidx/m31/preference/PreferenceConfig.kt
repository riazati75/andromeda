package ir.farsroidx.m31.preference

import androidx.annotation.Size
import ir.farsroidx.m31.AndromedaConfig
import ir.farsroidx.m31.AndromedaException
import ir.farsroidx.m31.additives.PREFERENCE_NAME
import ir.farsroidx.m31.additives.utils.TimeUnits

class PreferenceConfig : AndromedaConfig {

    internal var preferenceName: String = PREFERENCE_NAME
    internal var expirationTime: Int? = null
    internal var expirationUnit: TimeUnits? = null

    fun setPreferenceName(@Size(min = 1) prefName: String) {
        this.preferenceName = prefName
    }

    fun setExpirationTime(time: Int, unit: TimeUnits = TimeUnits.Minutes) {
        if (time < 1) {
            throw AndromedaException("Time cannot be less than [1].")
        }
        if (unit is TimeUnits.Seconds) {
            if (time < 15) {
                throw AndromedaException("Time cannot be less than 15 seconds [minimum = 15s].")
            }
        }
        this.expirationTime = time
        this.expirationUnit = unit
    }
}