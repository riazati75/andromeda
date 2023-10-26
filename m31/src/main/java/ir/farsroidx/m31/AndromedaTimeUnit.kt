package ir.farsroidx.m31

sealed class AndromedaTimeUnit(val value: Int) {
    data object Seconds : AndromedaTimeUnit(1)
    data object Minutes : AndromedaTimeUnit(60)
    data object Hour    : AndromedaTimeUnit(3_600)
    data object Day     : AndromedaTimeUnit(86_400)
    data object Week    : AndromedaTimeUnit(604_800)
    data object Month   : AndromedaTimeUnit(18_144_000)
    data object Year    : AndromedaTimeUnit(217_728_000)
}
