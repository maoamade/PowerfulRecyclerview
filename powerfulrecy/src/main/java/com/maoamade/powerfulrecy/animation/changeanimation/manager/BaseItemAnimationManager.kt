package com.maoamade.powerfulrecy.animation.changeanimation.manager

import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.BaseItemAnimator
import com.maoamade.powerfulrecy.animation.changeanimation.ItemAnimationInfo

/**

 * @Author maoamade

 * @Date 2021/7/6 4:04 PM

 * @Info BaseItemAnimationManager.kt

 */
abstract class BaseItemAnimationManager<T : ItemAnimationInfo>() {

    var mDefaultInterpolator: TimeInterpolator? = null
    lateinit var itemAnimator: BaseItemAnimator
    val mPending: MutableList<T> = mutableListOf()
    val mDeferredReadySets: MutableList<MutableList<T>> = mutableListOf()
    val mActive: MutableList<RecyclerView.ViewHolder> = mutableListOf()


    constructor(itemAnimator: BaseItemAnimator) {
        this.itemAnimator = itemAnimator
    }

    fun hasPending(): Boolean {
        return mPending.isNotEmpty()
    }

    fun isRunning(): Boolean {
        return mPending.isNotEmpty() || mDeferredReadySets.isNotEmpty() || mActive.isNotEmpty()
    }

    fun removeFromActive(viewHolder: RecyclerView.ViewHolder?) {
        mActive.remove(viewHolder)
    }

    fun cancelAllStartedAnimator() {
        val active = mActive
        for (index in active) {
            val itemview = index.itemView
            ViewCompat.animate(itemview).cancel()
        }
    }

    fun runPendingAnimations(deferred: Boolean, deferredDelay: Long) {
        if (deferred) {
            mDeferredReadySets.add(mPending)
            val process: Runnable = Runnable {
                for (index in mPending) {
                    createAnimation(index)
                }
                mPending.clear()
                mDeferredReadySets.remove(mPending)
            }
            mPending[0].getAvailableViewHolder()?.itemView?.let {
                ViewCompat.postOnAnimationDelayed(
                    it,
                    process,
                    deferredDelay
                )
            }

        } else {
            for (index in mPending) {
                createAnimation(index)
            }
            mPending.clear()
        }
    }

    fun endPendingAnimations(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let {
            for (index in mPending.asReversed()) {
                endNotStartedAnimation(index, viewHolder)
            }
        }
        mPending.clear()
    }

    fun endAllPendingAnimations() {
        endPendingAnimations(null)
    }

    fun endDeferredReadyAnimations(viewHolder: RecyclerView.ViewHolder?) {
        viewHolder?.let {
            for (deferredReadySet in mDeferredReadySets.asReversed()) {
                for (index in deferredReadySet.asReversed()) {
                    endNotStartedAnimation(index, viewHolder)
                }
            }
        }
        mDeferredReadySets.clear()
    }

    fun endAllDeferredAnimations() {
        endDeferredReadyAnimations(null)
    }

    private fun createAnimation(info: T) {
        onCreateAnimation(info)
    }

    fun endAnimation(holder: RecyclerView.ViewHolder?) {
        holder?.let {
            itemAnimator.endAnimation(it)
        }
    }

    fun dispatchFinishedWhenDone() {
        itemAnimator.dispatchAnimatorFinishWhenDone()
    }

    fun enqueuePendingAnimationInfo(info: T) {
        mPending.add(info)
    }

    fun resetAnimation(holder: RecyclerView.ViewHolder?) {
        if (mDefaultInterpolator == null) {
            mDefaultInterpolator = ValueAnimator().interpolator
        }
        holder?.itemView?.animate()?.interpolator = mDefaultInterpolator
        endAnimation(holder)
    }

    fun startActiveItemAnimaiton(
        info: T,
        holder: RecyclerView.ViewHolder?,
        animator: ViewPropertyAnimatorCompat
    ) {
        animator.setListener(BaseAnimatorListener(this, info, holder, animator))
        addActiveAnimationToTarget(holder)
        animator.start()
    }

    fun addActiveAnimationToTarget(holder: RecyclerView.ViewHolder?) {
        holder?.let {
            mActive.add(it)
        }
    }

    abstract fun endNotStartedAnimation(info: T?, viewHolder: RecyclerView.ViewHolder?): Boolean
    abstract fun dispatchStarting(info: T?, viewHolder: RecyclerView.ViewHolder?)
    abstract fun dispatchFinished(info: T?, viewHolder: RecyclerView.ViewHolder?)
    abstract fun setDuration(duration: Long)
    abstract fun getDuration(): Long
    abstract fun onCreateAnimation(info: T?)
    abstract fun onAnimationCancel(info: T?, holder: RecyclerView.ViewHolder?)
    abstract fun onAnimationEndSuccess(info: T?, holder: RecyclerView.ViewHolder?)
    abstract fun onAnimationEndBeforeStart(info: T?, holder: RecyclerView.ViewHolder?)
}

class BaseAnimatorListener<T : ItemAnimationInfo> : ViewPropertyAnimatorListener {

    var manager: BaseItemAnimationManager<T>? = null
    var info: T? = null
    var holder: RecyclerView.ViewHolder? = null
    var animator: ViewPropertyAnimatorCompat? = null

    constructor(
        manager: BaseItemAnimationManager<T>,
        info: T,
        holder: RecyclerView.ViewHolder?,
        animator: ViewPropertyAnimatorCompat
    ) {
        this.manager = manager
        this.info = info
        this.holder = holder
        this.animator = animator
    }

    override fun onAnimationEnd(view: View?) {
        manager?.onAnimationEndSuccess(info, holder)
        manager?.dispatchFinished(info, holder)
        holder?.let {
            info?.clear(it)
        }
        manager?.mActive?.remove(holder)
        manager?.dispatchFinishedWhenDone()

        this.animator?.setListener(null)
        manager = null
        info = null
        holder = null
        animator = null
    }

    override fun onAnimationCancel(view: View?) {
        manager?.onAnimationCancel(info, holder)
    }

    override fun onAnimationStart(view: View?) {
        manager?.dispatchStarting(info, holder)
    }

}