@file:Suppress("unused")

package ir.farsroidx.m31.additives

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import ir.farsroidx.m31.AndromedaActivity
import ir.farsroidx.m31.AndromedaFragment
import ir.farsroidx.m31.R

fun <T : ViewDataBinding> AndromedaFragment<*>.bottomSheet(invoker: (LayoutInflater) -> ViewBinding) =
    BottomSheetDialog(requireContext(), R.style.Style_Andromeda_BottomSheetDialog)
        .apply {
            setContentView(invoker(layoutInflater).root)
        }

fun <T : ViewDataBinding> AndromedaActivity<*>.bottomSheet(invoker: (LayoutInflater) -> ViewBinding) =
    BottomSheetDialog(this, R.style.Style_Andromeda_BottomSheetDialog)
        .apply {
            setContentView(invoker(layoutInflater).root)
        }

fun <T : ViewDataBinding> Context.bottomSheet(invoker: (LayoutInflater) -> ViewBinding) =
    BottomSheetDialog(this, R.style.Style_Andromeda_BottomSheetDialog)
        .apply {
            setContentView(invoker(layoutInflater).root)
        }

fun <T : ViewDataBinding> View.bottomSheet(invoker: (LayoutInflater) -> ViewBinding) =
    BottomSheetDialog(this.context, R.style.Style_Andromeda_BottomSheetDialog)
        .apply {
            setContentView(invoker(layoutInflater).root)
        }
