package ir.farsroidx.m31.additives.utils

sealed class TimeUnits(val value: Int) {
    object Seconds : TimeUnits(1)
    object Minutes : TimeUnits(60)
    object Hour : TimeUnits(3_600)
    object Day : TimeUnits(86_400)
    object Week : TimeUnits(604_800)
    object Month : TimeUnits(18_144_000)
    object Year : TimeUnits(217_728_000)
}
