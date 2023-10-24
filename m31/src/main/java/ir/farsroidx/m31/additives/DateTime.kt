package ir.farsroidx.m31.additives

import ir.farsroidx.m31.AndromedaTimeDifference

// TODO: DateTime ===================================================================== DateTime ===

fun dateTimeDifference(startTime: Long, endTime: Long): AndromedaTimeDifference {

    val diffTime = endTime - startTime

    return when {

        diffTime <= 15 ->
            AndromedaTimeDifference(
                value = diffTime,
                unit  = AndromedaTimeDifference.TimeDifferentUnit.NOW
            )

        diffTime in 15..60 ->
            AndromedaTimeDifference(
                value = diffTime,
                unit  = AndromedaTimeDifference.TimeDifferentUnit.SECONDS
            )

        diffTime in 61..3_599 ->
            AndromedaTimeDifference(
                value = diffTime / 60,
                unit  = AndromedaTimeDifference.TimeDifferentUnit.MINUTES
            )

        diffTime in 3_600..86_399 ->
            AndromedaTimeDifference(
                value = diffTime / 3_600,
                unit  = AndromedaTimeDifference.TimeDifferentUnit.HOUR
            )

        diffTime in 86_400..604_799 ->
            AndromedaTimeDifference(
                value = diffTime / 86_400,
                unit  = AndromedaTimeDifference.TimeDifferentUnit.DAY
            )

        diffTime in 604_800..2_591_999 ->
            AndromedaTimeDifference(
                value = diffTime / 604_800,
                unit  = AndromedaTimeDifference.TimeDifferentUnit.WEEK
            )

        diffTime in 2_592_000..31_103_999 ->
            AndromedaTimeDifference(
                value = diffTime / 2_592_000,
                unit  = AndromedaTimeDifference.TimeDifferentUnit.MONTH
            )

        else -> AndromedaTimeDifference(
            value = diffTime / 31_104_000,
            unit  = AndromedaTimeDifference.TimeDifferentUnit.YEAR
        )
    }
}