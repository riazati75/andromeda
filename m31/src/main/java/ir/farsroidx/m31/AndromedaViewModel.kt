@file:Suppress("MemberVisibilityCanBePrivate")

package ir.farsroidx.m31

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class AndromedaViewModel : ViewModel() {

    private var _onViewStateChange: (AndromedaViewState) -> Unit = {}

    private var _lifecycleOwner: LifecycleOwner? = null

    private val _liveViewState = MutableLiveData<AndromedaViewState>()
    val liveViewState: LiveData<AndromedaViewState> = _liveViewState

    internal fun setOnViewStateChanged(
        lifecycleOwner: LifecycleOwner, onChange: (AndromedaViewState) -> Unit
    ) {
        _lifecycleOwner    = lifecycleOwner
        _onViewStateChange = onChange
    }

    protected suspend fun doInIoScope(invoker: () -> Unit) {
        withContext(Dispatchers.IO) {
            invoker()
        }
    }

    protected suspend fun doInMainScope(invoker: () -> Unit) {
        withContext(Dispatchers.Main) {
            invoker()
        }
    }

    protected suspend fun doInDefaultScope(invoker: () -> Unit) {
        withContext(Dispatchers.Default) {
            invoker()
        }
    }

    protected fun viewModelScope(
        invoker: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(Dispatchers.IO) {
        invoker()
    }

    protected suspend fun setViewState(viewState: AndromedaViewState) {
        _lifecycleOwner?.let { lifecycleOwner ->
            doInMainScope {
                if (lifecycleOwner.lifecycle.currentState != Lifecycle.State.DESTROYED) {
                    _onViewStateChange( viewState )
                } else {
                    // TODO: Nothing to change =====================================================
                }
            }
        }
    }

    protected fun setLiveDataValue(viewState: AndromedaViewState) {
        _liveViewState.value = viewState
    }

    protected fun postLiveDataValue(viewState: AndromedaViewState) {
        _liveViewState.postValue(viewState)
    }
}
