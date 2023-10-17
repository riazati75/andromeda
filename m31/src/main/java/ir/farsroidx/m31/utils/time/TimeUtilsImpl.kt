package ir.farsroidx.m31.utils.time

internal class TimeUtilsImpl : TimeUtils {

    private lateinit var config: TimeConfig

    internal fun apply(config: TimeConfig): TimeUtilsImpl {
        this.config = config
        return this
    }

    override fun getDifferentTime(fromTime: Long, toTime: Long): TimeDifferent {
        val diffTime = toTime - fromTime
        return when {
            diffTime <= 15 -> TimeDifferent(value = diffTime.toInt(), TimeUnit.Now)
            diffTime in 15..60 -> TimeDifferent(value = diffTime.toInt(), TimeUnit.Seconds)
            diffTime in 61..3_599 -> TimeDifferent(
                value = (diffTime / 60).toInt(),
                TimeUnit.Minutes
            )

            diffTime in 3_600..86_399 -> TimeDifferent(
                value = (diffTime / 3_600).toInt(),
                TimeUnit.Hour
            )

            diffTime in 86_400..604_799 -> TimeDifferent(
                value = (diffTime / 86_400).toInt(),
                TimeUnit.Day
            )

            diffTime in 604_800..2_591_999 -> TimeDifferent(
                value = (diffTime / 604_800).toInt(),
                TimeUnit.Week
            )

            diffTime in 2_592_000..31_103_999 -> TimeDifferent(
                value = (diffTime / 2_592_000).toInt(),
                TimeUnit.Month
            )

            else -> TimeDifferent(value = (diffTime / 31_104_000).toInt(), TimeUnit.Year)
        }
    }
}