package ir.farsroidx.m31

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.farsroidx.m31.additives.koinInjector
import ir.farsroidx.m31.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class AndromedaViewStateViewModel <VS: Any> : AndromedaViewModel() {

    private var _onViewStateChange: (VS) -> Unit = {}

    private var _lifecycleOwner: LifecycleOwner? = null

    private val _viewStateChange = MutableLiveData<VS>()
    val viewStateChange: LiveData<VS> = _viewStateChange

    internal fun setOnViewStateChanged(lifecycleOwner: LifecycleOwner, onChange: (VS) -> Unit) {
        _lifecycleOwner    = lifecycleOwner
        _onViewStateChange = onChange
    }

    protected suspend fun setViewState(viewState: VS) {
        _lifecycleOwner?.let {
            doInMain {
                if (it.lifecycle.currentState != Lifecycle.State.DESTROYED) {
                    _onViewStateChange( viewState )
                } else {
                    // TODO: Nothing to change
                }
            }
        }
    }

    protected fun setLiveViewState(viewState: VS) {
        _viewStateChange.value = viewState
    }

    protected fun postLiveViewState(viewState: VS) {
        _viewStateChange.postValue(viewState)
    }
}