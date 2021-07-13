package com.maoamade.powerfulrecy.animation.changeanimation

import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.manager.*
import kotlin.math.max

/**

 * @Author maoamade

 * @Date 2021/7/6 4:03 PM

 * @Info GeneralItemAnimator.kt

 */
abstract class GeneralItemAnimator : BaseItemAnimator {

    var mAddManager: ItemAnimationAddManager? = null
    var mRemoveManager: ItemAnimationRemoveManager? = null
    var mChangeManager: ItemAnimationChangeManager? = null
    var mMoveManager: ItemAnimationMoveManager? = null

    constructor() {
        setManager()
    }

    private fun setManager() {
        onSetManager()
        check(!(mAddManager == null || mRemoveManager == null || mChangeManager == null || mMoveManager == null)) { "setManager isComplete" }
    }

    abstract fun onSetManager()

    override fun runPendingAnimations() {
        if (!hasPendingAnimations()) {
            return
        }
        onSchedulePendingAnimations()
    }

    override fun animateRemove(holder: RecyclerView.ViewHolder?): Boolean {
        return mRemoveManager?.addPendingAnimation(holder) ?: false
    }

    override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
        return mAddManager?.addPendingAnimation(holder) ?: false
    }

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromLeft: Int,
        fromTop: Int,
        toLeft: Int,
        toTop: Int
    ): Boolean {
        if (oldHolder == newHolder) {
            return mMoveManager?.addPendingAnimaiton(oldHolder, fromLeft, fromTop, toLeft, toTop)
                ?: false
        }
        return mChangeManager?.addPendingAnimation(
            oldHolder,
            newHolder,
            fromLeft,
            fromTop,
            toLeft,
            toTop
        ) ?: false
    }

    override fun animateMove(
        holder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {
        return mMoveManager?.addPendingAnimaiton(holder, fromX, fromY, toX, toY) ?: false
    }

    open fun cancelAnimations(holder: RecyclerView.ViewHolder?) {
        holder?.itemView?.let {
            ViewCompat.animate(it).cancel()
        }
    }

    override fun endAnimation(item: RecyclerView.ViewHolder) {
        cancelAnimations(item)
        mMoveManager?.endAllPendingAnimations()
        mMoveManager?.endAllDeferredAnimations()
        mAddManager?.endAllPendingAnimations()
        mAddManager?.endAllDeferredAnimations()
        mChangeManager?.endAllPendingAnimations()
        mChangeManager?.endAllDeferredAnimations()
        mRemoveManager?.endAllPendingAnimations()
        mRemoveManager?.endAllDeferredAnimations()

        dispatchAnimatorFinishWhenDone()
    }

    override fun isRunning(): Boolean =
        mMoveManager?.isRunning() ?: false
                || mAddManager?.isRunning() ?: false
                || mChangeManager?.isRunning() ?: false
                || mRemoveManager?.isRunning() ?: false

    override fun endAnimations() {
        mMoveManager?.endAllPendingAnimations()
        mMoveManager?.endAllDeferredAnimations()
        mAddManager?.endAllPendingAnimations()
        mAddManager?.endAllDeferredAnimations()
        mChangeManager?.endAllPendingAnimations()
        mChangeManager?.endAllDeferredAnimations()
        mRemoveManager?.endAllPendingAnimations()
        mRemoveManager?.endAllDeferredAnimations()

        if (!isRunning) {
            return
        }

        mMoveManager?.endAllDeferredAnimations()
        mRemoveManager?.endAllDeferredAnimations()
        mAddManager?.endAllDeferredAnimations()
        mChangeManager?.endAllDeferredAnimations()

        mMoveManager?.cancelAllStartedAnimator()
        mRemoveManager?.cancelAllStartedAnimator()
        mAddManager?.cancelAllStartedAnimator()
        mChangeManager?.cancelAllStartedAnimator()


        dispatchAnimationsFinished()
    }


    open fun onSchedulePendingAnimations() {
        schedulePendingAnimationsByDefaultRule()
    }

    /**
     * first do remove,next move & change,last add;
     * the animation will be delayed some time when
     * before is running
     */
    private fun schedulePendingAnimationsByDefaultRule() {
        val removalPending = if (mRemoveManager != null) mRemoveManager!!.hasPending() else false
        val movePending = if (mMoveManager != null) mMoveManager!!.hasPending() else false
        val addPending = if (mAddManager != null) mAddManager!!.hasPending() else false
        val changePending = if (mChangeManager != null) mChangeManager!!.hasPending() else false

        val removalDuration = if (removalPending) mRemoveManager?.getDuration() ?: 0 else 0
        val moveDuration = if (movePending) mMoveManager?.getDuration() ?: 0 else 0
        val changeDuration = if (changePending) mAddManager?.getDuration() ?: 0 else 0

        if (removalPending) {
            mRemoveManager?.runPendingAnimations(false, 0)
        }
        if (movePending) {
            mMoveManager?.runPendingAnimations(removalPending, removalDuration ?: 0)
        }
        if (changePending) {
            mChangeManager?.runPendingAnimations(removalPending, removalDuration ?: 0)
        }
        if (addPending) {
            val deferred: Boolean = removalPending || movePending || changePending
            val deferDelay = removalDuration + max(moveDuration, changeDuration)
            mAddManager?.runPendingAnimations(deferred, deferDelay)
        }
    }

    private fun hasPendingAnimations(): Boolean {
        return if (mRemoveManager != null && mAddManager != null && mMoveManager != null && mChangeManager != null) {
            mRemoveManager!!.hasPending() || mAddManager!!.hasPending() || mMoveManager!!.hasPending() || mChangeManager!!.hasPending()
        } else {
            false
        }
    }

    fun setAddManager(itemAddManager: ItemAnimationAddManager) {
        mAddManager = itemAddManager
    }

    fun getAddManager(): ItemAnimationAddManager? = mAddManager

    fun setMoveManager(itemMoveManager: ItemAnimationMoveManager) {
        mMoveManager = itemMoveManager
    }

    fun getMoveManager(): ItemAnimationMoveManager? = mMoveManager

    fun setChangeManager(itemChangeManager: ItemAnimationChangeManager) {
        mChangeManager = itemChangeManager
    }

    fun getChangeManager(): ItemAnimationChangeManager? = mChangeManager

    fun setRemoveManager(itemRemoveManager: ItemAnimationRemoveManager) {
        mRemoveManager = itemRemoveManager
    }

    fun getRemoveManager(): ItemAnimationRemoveManager? = mRemoveManager

}