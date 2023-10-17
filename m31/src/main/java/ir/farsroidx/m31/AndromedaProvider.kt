package ir.farsroidx.m31

import androidx.annotation.Size
import ir.farsroidx.m31.additives.PREFERENCE_NAME

sealed class AndromedaProvider {

    data class ExceptionUi(
        @Size(min = 1) val developerEmail: String
    ) : AndromedaProvider()

    data object Application : AndromedaProvider()

    data object Cache : AndromedaProvider()

    data object Crypto : AndromedaProvider()

    data object Database : AndromedaProvider()

    data object Download : AndromedaProvider()

    data class Memory(
        val expTime: Int?       = null,
        val expUnit: AndromedaTimeUnit? = null
    ) : AndromedaProvider()

    data object Network : AndromedaProvider()

    data class Preference(
        @Size(min = 1)
        val preferenceName: String     = PREFERENCE_NAME,
        val expirationTime: Int?       = null,
        val expirationUnit: AndromedaTimeUnit? = null
    ) : AndromedaProvider()

    data object Utils : AndromedaProvider()

}