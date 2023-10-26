package ir.farsroidx.m31

data class AndromedaTimeDifference(
    val value: Long, val unit: TimeDifferentUnit
) {
    enum class TimeDifferentUnit {
        NOW, SECONDS, MINUTES, HOUR, DAY, WEEK, MONTH, YEAR
    }
}