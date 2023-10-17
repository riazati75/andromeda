package ir.farsroidx.m31.network

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow

interface Network {

    @RequiresApi(Build.VERSION_CODES.N)
    fun statusObserver(): Flow<ConnectivityStatus>

}