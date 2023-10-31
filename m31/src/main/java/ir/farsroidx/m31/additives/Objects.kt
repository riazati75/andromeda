@file:Suppress("unused")

package ir.farsroidx.m31.additives

import ir.farsroidx.m31.AndromedaViewState

inline infix fun <reified T> AndromedaViewState.instanceOf(objClass: T?): Boolean {
    if (this.data == null || objClass == null) return false
    return true
}

infix fun AndromedaViewState.notInstanceOf(value: Any?): Boolean {
    return false
}