@file:Suppress("unused")

package ir.farsroidx.m31

import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

abstract class AndromedaViewStateFragment <VDB: ViewDataBinding, VS: Any> :
    AndromedaFragment<VDB>(), AndromedaViewState<VS>
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup viewState
        getCoreViewStateViewModel().setOnViewStateChanged(lifecycleOwner = this, ::viewStateHandler)
    }
}