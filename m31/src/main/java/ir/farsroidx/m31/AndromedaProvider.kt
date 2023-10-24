package ir.farsroidx.m31

import androidx.annotation.IntRange
import androidx.annotation.Size
import ir.farsroidx.m31.additives.isValidEmail

sealed class AndromedaProvider {

    // TODO: === ExceptionUi ======================================================= ExceptionUi ===

    data class ExceptionUi(
        @Size(min = 1) val developerEmail: String
    ) : AndromedaProvider() {
        init {
            if (!developerEmail.isValidEmail()) {
                throw AndromedaException("Developer Email is Invalid.")
            }
        }
    }

    // TODO: === Application ======================================================= Application ===

    data object Application : AndromedaProvider()

    // TODO: === Cache =================================================================== Cache ===

    data object Cache : AndromedaProvider()

    // TODO: === Crypto ================================================================= Crypto ===

    data object Crypto : AndromedaProvider()

    // TODO: === Database ============================================================= Database ===

    data object Database : AndromedaProvider()

    // TODO: === Download ============================================================= Download ===

    data object Download : AndromedaProvider()

    // TODO: === Memory ================================================================= Memory ===

    data class Memory(

        @IntRange(from = 1)
        val expTime: Int?               = null,

        val expUnit: AndromedaTimeUnit? = null

    ) : AndromedaProvider() {
        init {
            expUnit?.let {
                if (it is AndromedaTimeUnit.Seconds && (expTime ?: 0) < 15) {
                    throw AndromedaException(
                        "Time cannot be less than 15 seconds [minimum = 15s]."
                    )
                }
            }
        }
    }

    // TODO: === Network =============================================================== Network ===

    data class Network(

        @Size(min = 1)
        val baseUrl: String,

        @IntRange(from = 5_000)
        val timeOut: Long = 15_000L

    ) : AndromedaProvider() {
        init {
            if (!baseUrl.endsWith("/")) {
                throw AndromedaException(
                    "Base url must be ends with '/'."
                )
            }
        }
    }

    // TODO: === Preference ========================================================= Preference ===

    data class Preference(

        @Size(min = 1)
        val name: String                = AndromedaConstants.PREFERENCE_NAME,

        @IntRange(from = 1)
        val expTime: Int?               = null,

        val expUnit: AndromedaTimeUnit? = null

    ) : AndromedaProvider() {
        init {
            expUnit?.let {
                if (it is AndromedaTimeUnit.Seconds && (expTime ?: 0) < 15) {
                    throw AndromedaException(
                        "Time cannot be less than 15 seconds [minimum = 15s]."
                    )
                }
            }
        }
    }
}