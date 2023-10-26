@file:Suppress("unused")

package ir.farsroidx.m31.additives

import android.telephony.PhoneNumberUtils
import android.util.Patterns
import android.widget.EditText

// TODO: Validation ================================================================= Validation ===

fun String.isValidIranianNationalCode() = this.takeIf {
    it.length == 10
}?.mapNotNull(Char::digitToIntOrNull)?.takeIf { it.size == 10 }?.let {
    val check = it[9]
    val sum = it.slice(0..8).mapIndexed { i, x -> x * (10 - i) }.sum() % 11
    if (sum < 2) check == sum else check + sum == 11
} ?: false

fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun EditText.isValidPhone(prefix: String = "09"): Boolean {
    return readString().startsWith(prefix) && PhoneNumberUtils.isGlobalPhoneNumber(readString())
}