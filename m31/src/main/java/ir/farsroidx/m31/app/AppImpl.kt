@file:Suppress("DEPRECATION")

package ir.farsroidx.m31.app

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.provider.Settings.Secure
import androidx.appcompat.app.AppCompatDelegate
import ir.farsroidx.m31.additives.killApplication
import java.util.Locale

@SuppressLint("HardwareIds")
internal class AppImpl(
    private val context: Context,
) : App {

    private val androidDeviceId by lazy {
        Secure.getString(
            context.applicationContext.contentResolver, Secure.ANDROID_ID
        )
    }

    private val clipboardManager by lazy {
        context.getSystemService(Context.CLIPBOARD_SERVICE)
            as ClipboardManager
    }

    override fun getDeviceId(): String = androidDeviceId

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

    override fun pasteFromClipboard(): CharSequence? {
        return try {
            val clip = clipboardManager.primaryClip
            val item = clip?.getItemAt(0)
            return item?.text
        } catch (th: Throwable) {
            th.printStackTrace()
            null
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

    // set auto save pref
    override fun setThemeMode(@AppThemeMode mode: Int) {
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    override fun killProcess() {
        killApplication()
    }
}