package com.maoamade.powerfulrecy.animation.changeanimation.manager

import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.BaseItemAnimator
import com.maoamade.powerfulrecy.animation.changeanimation.info.AnimationMoveInfo

/**

 * @Author maoamade

 * @Date 2021/7/8 3:39 PM

 * @Info ItemAnimationMoveManager.kt

 */
abstract class ItemAnimationMoveManager : BaseItemAnimationManager<AnimationMoveInfo> {

    constructor(itemAnimator: BaseItemAnimator) {
        this.itemAnimator = itemAnimator
    }
    override fun dispatchStarting(info: AnimationMoveInfo?, viewHolder: RecyclerView.ViewHolder?) {
        itemAnimator.dispatchMoveStarting(viewHolder)
    }

    override fun dispatchFinished(info: AnimationMoveInfo?, viewHolder: RecyclerView.ViewHolder?) {
        itemAnimator.dispatchMoveFinished(viewHolder)
    }

    override fun getDuration(): Long = itemAnimator.moveDuration

    override fun setDuration(duration: Long) {
        itemAnimator.moveDuration = duration
    }

    override fun endNotStartedAnimation(
        info: AnimationMoveInfo?,
        viewHolder: RecyclerView.ViewHolder?
    ): Boolean {
        return if ((info?.mHolder != null && viewHolder != null) || info?.mHolder == viewHolder) {
            onAnimationEndBeforeStart(info, info?.mHolder)
            dispatchFinished(info, info?.mHolder)
            info?.clear(info.mHolder)
            true
        } else {
            false
        }
    }

    abstract fun addPendingAnimaiton(
        viewHolder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ):Boolean
}