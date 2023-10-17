package ir.farsroidx.m31.utils.time

sealed class TimeUnit {
    data object Now : TimeUnit()
    data object Seconds : TimeUnit()
    data object Minutes : TimeUnit()
    data object Hour : TimeUnit()
    data object Day : TimeUnit()
    data object Week : TimeUnit()
    data object Month : TimeUnit()
    data object Year : TimeUnit()
}

data class TimeDifferent(val value: Int, val unit: TimeUnit)