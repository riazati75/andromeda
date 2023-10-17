package ir.farsroidx.m31.additives

import android.util.Patterns
import ir.farsroidx.m31.AndromedaException

// TODO: Validation ================================================================= Validation ===

fun String.isValidIranianNationalCode() = this.takeIf {
    it.length == 10
}?.mapNotNull(Char::digitToIntOrNull)?.takeIf { it.size == 10 }?.let {
    val check = it[9]
    val sum = it.slice(0..8).mapIndexed { i, x -> x * (10 - i) }.sum() % 11
    if (sum < 2) check == sum else check + sum == 11
} ?: false

fun validationEmail(email: String) {
    val isSuccess = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    if (!isSuccess) {
        throw AndromedaException("Developer Email is Invalid.")
    }
}