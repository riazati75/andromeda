package ir.farsroidx.m31.app

import java.util.Locale

interface App {

    fun copyToClipboard(label: String, data: String): Boolean

    fun pasteFromClipboard(): CharSequence

    fun setLanguage(iLocale: Locale)

    fun setThemeMode(@AppThemeMode mode: Int)

}