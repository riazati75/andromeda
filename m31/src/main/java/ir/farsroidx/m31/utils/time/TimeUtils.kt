package ir.farsroidx.m31.utils.time

interface TimeUtils {

    fun getDifferentTime(
        fromTime: Long, toTime: Long = System.currentTimeMillis() / 1_000L
    ): TimeDifferent

}