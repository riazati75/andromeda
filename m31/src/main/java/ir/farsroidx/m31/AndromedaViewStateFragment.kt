@file:Suppress("unused")

package ir.farsroidx.m31

import android.os.Bundle
import androidx.databinding.ViewDataBinding

abstract class AndromedaViewStateFragment <VDB: ViewDataBinding, VS: Any> :
    AndromedaFragment<VDB>(), AndromedaViewState<VS>
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup viewState
        getAndromedaViewModel().setOnViewStateChanged(lifecycleOwner = this, ::viewStateHandler)
    }
}