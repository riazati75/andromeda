package ir.farsroidx.m31.app

import android.Manifest
import androidx.annotation.RequiresPermission
import java.util.Locale

interface App {

    @RequiresPermission(
        Manifest.permission.READ_PHONE_STATE
    )
    fun getDeviceId(): String

    fun copyToClipboard(label: String, data: String): Boolean

    fun pasteFromClipboard(): CharSequence?

    fun setLanguage(iLocale: Locale)

    fun setThemeMode(@AppThemeMode mode: Int)

    fun killProcess()

}