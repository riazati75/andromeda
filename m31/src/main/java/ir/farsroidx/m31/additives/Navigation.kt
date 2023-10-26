package ir.farsroidx.m31.additives

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import ir.farsroidx.m31.AndromedaActivity
import ir.farsroidx.m31.AndromedaFragment

// TODO: Navigation ================================================================= Navigation ===

fun AndromedaFragment<*>.navigateUp(requestCode: Int, bundle: Bundle) {

    if (activity != null && activity is AndromedaActivity<*>) {

        (activity as AndromedaActivity<*>).setBundle(requestCode, bundle)

    } else throw IllegalStateException(
        "You need to extend your activity from AndromedaFragment<*>"
    )

    findNavController().navigateUp()
}