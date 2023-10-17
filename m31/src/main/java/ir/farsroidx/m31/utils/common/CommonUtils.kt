package ir.farsroidx.m31.utils.common

interface CommonUtils {

    fun log(log: Any?, type: LogType = LogType.DEBUG, saveToFile: Boolean = false)

    fun dpToPx(dp: Int): Int

    fun pxToDp(px: Int): Int

}