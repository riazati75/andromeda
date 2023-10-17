package ir.farsroidx.m31.additives

import ir.farsroidx.m31.network.NetworkCallback

// TODO: Network =============================================================================== ///

internal fun networkCallback(
    invoker: NetworkCallback.Builder.() -> Unit = {}
): NetworkCallback {
    return NetworkCallback.Builder().apply(invoker)
        .build()
}