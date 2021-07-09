package com.maoamade.powerfulrecy.animation.changeanimation

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator

abstract class BaseItemAnimator : SimpleItemAnimator() {

    var mListener: ItemChangeAnimatorListener? = null

    /**
     *  an internal fun called when the animator finish no animation happens
     *  it's unavailable for developers to create directly
     */
    fun setListener(listener: ItemChangeAnimatorListener) {
        mListener = listener
    }

    override fun onAddStarting(item: RecyclerView.ViewHolder?) {
        super.onAddStarting(item)
        onAddStartingImpl(item)
    }

    override fun onAddFinished(item: RecyclerView.ViewHolder?) {
        super.onAddFinished(item)
        onAddFinishedImpl(item)
        mListener?.onAddDone(item)
    }

    override fun onRemoveStarting(item: RecyclerView.ViewHolder?) {
        super.onRemoveStarting(item)
        onRemoveStartingImpl(item)
    }

    override fun onRemoveFinished(item: RecyclerView.ViewHolder?) {
        super.onRemoveFinished(item)
        onRemoveFinishedImpl(item)
        mListener?.onRemoveDone(item)
    }

    override fun onChangeStarting(item: RecyclerView.ViewHolder?, oldItem: Boolean) {
        super.onChangeStarting(item, oldItem)
        onChangeStartingImpl(item)
    }

    override fun onChangeFinished(item: RecyclerView.ViewHolder?, oldItem: Boolean) {
        super.onChangeFinished(item, oldItem)
        onChangeFinishedImpl(item)
        mListener?.onChangeDone(item)
    }

    override fun onMoveStarting(item: RecyclerView.ViewHolder?) {
        super.onMoveStarting(item)
        onMoveStartingImpl(item)
    }

    override fun onMoveFinished(item: RecyclerView.ViewHolder?) {
        super.onMoveFinished(item)
        onMoveFinishedImpl(item)
        mListener?.onMoveDone(item)
    }

    open fun onAddStartingImpl(item: RecyclerView.ViewHolder?) {}
    open fun onAddFinishedImpl(item: RecyclerView.ViewHolder?) {}
    open fun onRemoveStartingImpl(item: RecyclerView.ViewHolder?) {}
    open fun onRemoveFinishedImpl(item: RecyclerView.ViewHolder?) {}
    open fun onChangeStartingImpl(item: RecyclerView.ViewHolder?) {}
    open fun onChangeFinishedImpl(item: RecyclerView.ViewHolder?) {}
    open fun onMoveStartingImpl(item: RecyclerView.ViewHolder?) {}
    open fun onMoveFinishedImpl(item: RecyclerView.ViewHolder?) {}

    /**
     * This method should be called by ItemAnimator implementations to notify
     * any listeners that all pending and active item animations are finished.
     */
    fun dispatchAnimatorFinishWhenDone(): Boolean {
        return if (!isRunning) {
            dispatchAnimationsFinished()
            true
        } else {
            false
        }
    }
}

/**
 * an internal interface used by item animation,
 * it's unavailable for developers to create directly
 */
interface ItemChangeAnimatorListener {
    fun onAddDone(item: RecyclerView.ViewHolder?)
    fun onRemoveDone(item: RecyclerView.ViewHolder?)
    fun onChangeDone(item: RecyclerView.ViewHolder?)
    fun onMoveDone(item: RecyclerView.ViewHolder?)
}