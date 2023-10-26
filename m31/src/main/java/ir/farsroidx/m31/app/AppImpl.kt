@file:Suppress("DEPRECATION")

package ir.farsroidx.m31.app

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import java.util.Locale

internal class AppImpl(private val context: Context) : App {

    private val clipboardManager by lazy {
        context.getSystemService(Context.CLIPBOARD_SERVICE)
            as ClipboardManager
    }

    override fun copyToClipboard(label: String, data: String): Boolean {
        return try {
            clipboardManager.setPrimaryClip(
                ClipData.newPlainText(
                    label, data
                )
            )
            true
        } catch (th: Throwable) {
            th.printStackTrace()
            false
        }
    }

    override fun pasteFromClipboard(): CharSequence {
        return try {
            val clip = clipboardManager.primaryClip
            val item = clip?.getItemAt(0)
            return item?.text ?: ""
        } catch (th: Throwable) {
            th.printStackTrace()
            ""
        }
    }

    override fun setLanguage(iLocale: Locale) {
        context.resources.updateConfiguration(
            context.resources.configuration.apply {
                locale = iLocale
            },
            context.resources.displayMetrics
        )
    }

    // Set Auto Save Pref

    override fun setThemeMode(@AppThemeMode mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}