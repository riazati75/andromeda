package ir.farsroidx.m31.utils.time

sealed class TimeUnit {
    object Now : TimeUnit()
    object Seconds : TimeUnit()
    object Minutes : TimeUnit()
    object Hour : TimeUnit()
    object Day : TimeUnit()
    object Week : TimeUnit()
    object Month : TimeUnit()
    object Year : TimeUnit()
}

data class TimeDifferent(val value: Int, val unit: TimeUnit)