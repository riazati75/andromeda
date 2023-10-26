@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package ir.farsroidx.m31

import android.os.Bundle
import androidx.databinding.ViewDataBinding

abstract class AndromedaViewStateActivity <VDB: ViewDataBinding, VS: Any> :
    AndromedaActivity<VDB>(), AndromedaViewState<VS>
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setup viewState
        getAndromedaViewModel().setOnViewStateChanged(lifecycleOwner = this, ::viewStateHandler)
    }
}
