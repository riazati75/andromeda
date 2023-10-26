@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package ir.farsroidx.m31

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ir.farsroidx.m31.additives.autoViewDataBinding

abstract class AndromedaRecyclerAdapter<VDB : ViewDataBinding, M : Any>
    : RecyclerView.Adapter<AndromedaRecyclerAdapter.AndromedaViewHolder<VDB>>() {

    // Selected Item After Filter

    protected lateinit var context: Context
    protected lateinit var recyclerView: RecyclerView

    private lateinit var layoutInflater: LayoutInflater

    protected var action1Clicked    : (item: M) -> Unit = {}
    protected var action1LongClicked: (item: M) -> Boolean = { false }

    protected var action2Clicked    : (item: M) -> Unit = {}
    protected var action2LongClicked: (item: M) -> Boolean = { false }

    protected var action3Clicked    : (item: M) -> Unit = {}
    protected var action3LongClicked: (item: M) -> Boolean = { false }

    protected var action4Clicked    : (item: M) -> Unit = {}
    protected var action4LongClicked: (item: M) -> Boolean = { false }

    protected var action5Clicked    : (item: M) -> Unit = {}
    protected var action5LongClicked: (item: M) -> Boolean = { false }

    protected var itemClicked: (item: M) -> Unit = {}

    protected var itemLongClicked: (item: M) -> Unit = {}

    protected var onItemChange: (isEmpty: Boolean) -> Unit = {}

    private val mSelectedItems = mutableListOf<Int>()

    class AndromedaViewHolder<DB : ViewDataBinding>(val dataBinding: DB) :
        RecyclerView.ViewHolder(dataBinding.root)

    private val mItems = mutableListOf<M>()

    private var mItemsBackup: List<M>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AndromedaViewHolder<VDB> {
        return AndromedaViewHolder(
            autoViewDataBinding(
                context, layoutInflater, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AndromedaViewHolder<VDB>, position: Int) {
        onBindViewHolder(holder.dataBinding, mItems[position], position)
    }

    @CallSuper
    open fun addItem(item: M) {
        synchronized(mItems) {
            this.mItems.add(item)
            notifyItemInserted(itemCount - 1)
            onItemChange(itemCount == 0)
        }
    }

    @CallSuper
    open fun addItems(vararg items: M) {
        synchronized(mItems) {
            val iCount = itemCount
            this.mItems.addAll(items)
            notifyItemRangeInserted(iCount, itemCount)
            onItemChange(itemCount == 0)
        }
    }

    @CallSuper
    open fun addItems(items: List<M>) {
        synchronized(mItems) {
            val iCount = itemCount
            this.mItems.addAll(items)
            notifyItemRangeInserted(iCount, itemCount)
            onItemChange(itemCount == 0)
        }
    }

    @CallSuper
    open fun addItemAt(position: Int, item: M) {
        synchronized(mItems) {
            this.mItems.add(position, item)
            notifyItemInserted(position)
            notifyItemRangeChanged(position, itemCount)
            onItemChange(itemCount == 0)
        }
    }

    @CallSuper
    open fun addItemsAt(position: Int, vararg items: M) {
        synchronized(mItems) {
            this.mItems.addAll(position, items.toList())
            notifyItemRangeInserted(position, items.size)
            notifyItemRangeChanged(position, itemCount)
            onItemChange(itemCount == 0)
        }
    }

    @CallSuper
    open fun addItemsAt(position: Int, items: List<M>) {
        synchronized(mItems) {
            this.mItems.addAll(position, items)
            notifyItemRangeInserted(position, items.size)
            notifyItemRangeChanged(position, itemCount)
            onItemChange(itemCount == 0)
        }
    }

    @CallSuper
    open fun updateItem(item: M) {
        synchronized(mItems) {
            val position = this.mItems.indexOf(item)
            this.mItems[position] = item
            notifyItemChanged(position)
            onItemChange(itemCount == 0)
        }
    }

    @CallSuper
    open fun updateItemAt(position: Int, items: M) {
        synchronized(mItems) {
            this.mItems[position] = items
            notifyItemChanged(position)
            onItemChange(itemCount == 0)
        }
    }

    @CallSuper
    open fun removeItem(item: M) {
        synchronized(mItems) {
            val position = this.mItems.indexOf(item)
            this.mItems.remove(item)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
            onItemChange(itemCount == 0)
        }
    }

    @CallSuper
    open fun removeItemAt(position: Int) {
        synchronized(mItems) {
            this.mItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
            onItemChange(itemCount == 0)
        }
    }

    @CallSuper
    open fun clear() {
        synchronized(mItems) {
            if (itemCount > 0) {
                val lastItemCount = itemCount
                this.mItems.clear()
                notifyItemRangeRemoved(0, lastItemCount)
                notifyItemChanged(0, itemCount)
                this.mItemsBackup = null
                onItemChange(itemCount == 0)
            }
        }
    }

    fun setOnAction1Clicked(onClicked: (item: M) -> Unit) {
        this.action1Clicked = onClicked
    }

    fun setOnAction1LongClicked(onClicked: (item: M) -> Boolean) {
        this.action1LongClicked = onClicked
    }

    fun setOnAction2Clicked(onClicked: (item: M) -> Unit) {
        this.action2Clicked = onClicked
    }

    fun setOnAction2LongClicked(onClicked: (item: M) -> Boolean) {
        this.action2LongClicked = onClicked
    }

    fun setOnAction3Clicked(onClicked: (item: M) -> Unit) {
        this.action3Clicked = onClicked
    }

    fun setOnAction3LongClicked(onClicked: (item: M) -> Boolean) {
        this.action3LongClicked = onClicked
    }

    fun setOnAction4Clicked(onClicked: (item: M) -> Unit) {
        this.action4Clicked = onClicked
    }

    fun setOnAction41LongClicked(onClicked: (item: M) -> Boolean) {
        this.action4LongClicked = onClicked
    }

    fun setOnAction5Clicked(onClicked: (item: M) -> Unit) {
        this.action5Clicked = onClicked
    }

    fun setOnAction5LongClicked(onClicked: (item: M) -> Boolean) {
        this.action5LongClicked = onClicked
    }

    fun setOnItemClicked(onClicked: (item: M) -> Unit) {
        this.itemClicked = onClicked
    }

    fun setOnItemLongClicked(onLongClicked: (item: M) -> Unit) {
        this.itemLongClicked = onLongClicked
    }

    fun setOnValueChange(onValueChange: (isEmpty: Boolean) -> Unit) {
        this.onItemChange = onValueChange
    }

    protected abstract fun onBindViewHolder(dataBinding: VDB, item: M, position: Int)

    protected abstract fun onViewRecycled(dataBinding: VDB)

    @CallSuper
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
        this.context = recyclerView.context
        this.layoutInflater = LayoutInflater.from(context)
    }

    @CallSuper
    override fun onViewRecycled(holder: AndromedaViewHolder<VDB>) {
        super.onViewRecycled(holder)
        onViewRecycled(holder.dataBinding)
    }

    @CallSuper
    override fun getItemCount(): Int = mItems.size

    protected fun getColorById(@ColorRes resId: Int): Int {
        return ContextCompat.getColor(context, resId)
    }

    protected fun getStringById(@StringRes resId: Int): String {
        return context.getString(resId)
    }

    protected fun getDrawableById(@DrawableRes resId: Int): Drawable? {
        return ContextCompat.getDrawable(context, resId)
    }

    protected fun getItems(): List<M> = mItems

    fun clearFilters() {
        if (mItemsBackup != null) {
            notifyItemRangeRemoved(0, itemCount)
            mItems.clear()
            mItems.addAll(mItemsBackup!!)
            notifyItemRangeInserted(0, itemCount)
            mItemsBackup = null
        }
    }

    @Synchronized
    fun filterItems(filterBlock: (M) -> Boolean) {
        if (mItemsBackup == null) {
            mItemsBackup = mutableListOf<M>().apply {
                addAll(mItems)
            }
        }
        mItemsBackup!!.filter(filterBlock).apply {
            notifyItemRangeRemoved(0, itemCount)
            mItems.clear()
            mItems.addAll(this)
            notifyItemRangeInserted(0, itemCount)
        }
    }

    protected fun addSelectedPosition(position: Int) {
        synchronized(mSelectedItems) {
            mSelectedItems.add(position)
            notifyItemChanged(position)
        }
    }

    protected fun removeSelectedPosition(position: Int) {
        synchronized(mSelectedItems) {
            if (mSelectedItems.contains(position)) {
                mSelectedItems.remove(position)
                notifyItemChanged(position)
            }
        }
    }

    protected fun getSelectedPositions(): List<Int> = mSelectedItems

    protected fun isSelectedPosition(position: Int): Boolean = mSelectedItems.contains(position)

    fun getSelectedItems(): List<M> {
        return getItems().filterIndexed { index, _ ->
            mSelectedItems.contains(index)
        }
    }
}
