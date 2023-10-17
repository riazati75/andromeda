package ir.farsroidx.m31.network

import android.net.ConnectivityManager
import android.net.Network

internal class NetworkCallback(
    private val builder: Builder
) : ConnectivityManager.NetworkCallback() {

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        builder.available()
    }

    override fun onLosing(network: Network, maxMsToLive: Int) {
        super.onLosing(network, maxMsToLive)
        builder.losing()
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        builder.lost()
    }

    override fun onUnavailable() {
        super.onUnavailable()
        builder.unavailable()
    }

    class Builder {

        var available: (() -> Unit) = { }
            private set

        var losing: (() -> Unit) = { }
            private set

        var lost: (() -> Unit) = { }
            private set

        var unavailable: (() -> Unit) = { }
            private set

        fun onAvailable(onAvailable: () -> Unit) {
            this.available = onAvailable
        }

        fun onLosing(onLosing: () -> Unit) {
            this.losing = onLosing
        }

        fun onLost(onLost: () -> Unit) {
            this.lost = onLost
        }

        fun onUnavailable(onUnavailable: () -> Unit) {
            this.unavailable = onUnavailable
        }

        fun build(): NetworkCallback {
            return NetworkCallback(this)
        }
    }
}