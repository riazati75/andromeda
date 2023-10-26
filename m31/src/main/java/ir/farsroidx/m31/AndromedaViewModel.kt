package ir.farsroidx.m31

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.farsroidx.m31.additives.koinInjector
import ir.farsroidx.m31.dispatcher.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class AndromedaViewModel : ViewModel() {

    protected val dispatcher: Dispatcher by koinInjector()

    protected suspend fun doInIo(block: () -> Unit) {
        withContext(dispatcher.io) {
            block()
        }
    }

    protected suspend fun doInMain(block: () -> Unit) {
        withContext(dispatcher.main) {
            block()
        }
    }

    protected suspend fun doInDefault(block: () -> Unit) {
        withContext(dispatcher.default) {
            block()
        }
    }

    protected fun viewModelScope(
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(dispatcher.io) {
        block()
    }
}
