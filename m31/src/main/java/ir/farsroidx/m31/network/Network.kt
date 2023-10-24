package ir.farsroidx.m31.network

import android.Manifest
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import kotlinx.coroutines.flow.Flow

interface Network {

    @RequiresApi(Build.VERSION_CODES.N)
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    fun observeNetworkStatus(): Flow<ConnectivityStatus>

    @RequiresPermission(Manifest.permission.INTERNET)
    suspend fun get(): String

    @RequiresPermission(Manifest.permission.INTERNET)
    suspend fun post(): String

}