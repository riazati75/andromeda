package ir.farsroidx.m31.utils.common

import android.util.Log
import ir.farsroidx.m31.cache.Cache

internal class CommonUtilsImpl(
    private val cache: Cache
) : CommonUtils {

    private lateinit var config: CommonConfig

    internal fun apply(config: CommonConfig): CommonUtils {
        this.config = config
        return this
    }

    override fun log(log: Any?, type: LogType, saveToFile: Boolean) {
        if (log == null) return
        when (type) {
            LogType.VERBOSE -> Log.v("", log.toString())
            LogType.DEBUG -> Log.d("", log.toString())
            LogType.INFO -> Log.i("", log.toString())
            LogType.WARNING -> Log.w("", log.toString())
            LogType.ERROR -> Log.e("", log.toString())
        }
    }

    override fun dpToPx(dp: Int): Int {
        TODO("Not yet implemented")
    }

    override fun pxToDp(px: Int): Int {
        TODO("Not yet implemented")
    }

}