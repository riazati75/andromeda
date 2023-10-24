@file:Suppress("unused")

package ir.farsroidx.m31.additives

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import ir.farsroidx.m31.R

fun <T : ViewDataBinding> Fragment.bottomSheet(invoker: (LayoutInflater) -> ViewBinding) =
    requireContext().bottomSheet<T>(invoker)

fun <T : ViewDataBinding> Activity.bottomSheet(invoker: (LayoutInflater) -> ViewBinding) =
    baseContext.bottomSheet<T>(invoker)

fun <T : ViewDataBinding> View.bottomSheet(invoker: (LayoutInflater) -> ViewBinding) =
    context.bottomSheet<T>(invoker)

fun <T : ViewDataBinding> Context.bottomSheet(invoker: (LayoutInflater) -> ViewBinding) =
    BottomSheetDialog(this, R.style.Theme_Andromeda_BottomSheetDialog)
        .apply {
            setContentView(invoker(layoutInflater).root)
        }
