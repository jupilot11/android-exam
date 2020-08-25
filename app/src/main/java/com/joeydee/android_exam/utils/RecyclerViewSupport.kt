package com.joeydee.android_exam.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewSupport(private val target: RecyclerView) : RecyclerView.OnChildAttachStateChangeListener{
    private var onClickListener: ((RecyclerView, Int, View) -> Unit)? = null
    private var onLongClickListener: ((RecyclerView, Int, View) -> Unit)? = null
    fun onClick(listener: (RecyclerView, Int, View) -> Unit): RecyclerViewSupport {
        onClickListener = listener
        return this
    }
    fun onLongClick(listener: (RecyclerView, Int, View) -> Unit): RecyclerViewSupport {
        onLongClickListener = listener
        return this
    }
    override fun onChildViewAttachedToWindow(view: View) {
        val holder = target.getChildViewHolder(view)
        val position = holder.adapterPosition
        onClickListener?.let {
            view.setOnClickListener { v ->
                it.invoke(target, position, v)
            }
        }
        onLongClickListener?.let {
            view.setOnLongClickListener { v ->
                it.invoke(target, position, v)
                true
            }
        }
    }
    override fun onChildViewDetachedFromWindow(view: View) {}
    companion object {
        fun on(target: RecyclerView): RecyclerViewSupport =
            RecyclerViewSupport(target).also {
                target.addOnChildAttachStateChangeListener(it)
            }
    }
}

fun RecyclerView.onClick(handler: (RecyclerView, Int, View) -> Unit): RecyclerViewSupport {
    return RecyclerViewSupport
        .on(this)
        .onClick(handler)
}

fun RecyclerView.onLongClick(handler: (RecyclerView, Int, View) -> Unit): RecyclerViewSupport {
    return RecyclerViewSupport
        .on(this)
        .onLongClick(handler)
}