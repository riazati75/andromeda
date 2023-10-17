package ir.farsroidx.m31

import androidx.lifecycle.ViewModel
import ir.farsroidx.m31.additives.koinInjector
import ir.farsroidx.m31.dispatcher.Dispatcher

abstract class AndromedaViewModel : ViewModel() {

    protected val dispatcher: Dispatcher by koinInjector()

}
