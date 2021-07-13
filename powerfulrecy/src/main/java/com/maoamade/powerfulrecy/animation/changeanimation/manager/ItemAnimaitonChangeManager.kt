package com.maoamade.powerfulrecy.animation.changeanimation.manager

import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.BaseItemAnimator
import com.maoamade.powerfulrecy.animation.changeanimation.info.AnimationChangeInfo

/**

 * @Author maoamade

 * @Date 2021/7/8 4:09 PM

 * @Info ItemAnimaitonChangeManager.kt

 */
abstract class ItemAnimationChangeManager(itemAnimator1: BaseItemAnimator) : BaseItemAnimationManager<AnimationChangeInfo>(itemAnimator1) {

    override fun dispatchStarting(
        info: AnimationChangeInfo?,
        viewHolder: RecyclerView.ViewHolder?
    ) {
        itemAnimator.dispatchChangeStarting(viewHolder, info?.oldHolder == viewHolder)
    }

    override fun dispatchFinished(
        info: AnimationChangeInfo?,
        viewHolder: RecyclerView.ViewHolder?
    ) {
        itemAnimator.dispatchChangeFinished(viewHolder, info?.oldHolder == viewHolder)
    }

    override fun getDuration(): Long = if (itemAnimator.changeDuration >0) itemAnimator.changeDuration else 3000

    override fun setDuration(duration: Long) {
        itemAnimator.changeDuration = duration
    }

    override fun onCreateAnimation(info: AnimationChangeInfo?) {
        if (info?.oldHolder != null) {
            onCreateAnimationChangeForOldItem(info)
        }
        if (info?.newHolder != null) {
            onCreateAnimationChangeForNewItem(info)
        }
    }

    override fun endNotStartedAnimation(
        info: AnimationChangeInfo?,
        viewHolder: RecyclerView.ViewHolder?
    ): Boolean {
        if ((info?.oldHolder != null && viewHolder != null) || info?.oldHolder == viewHolder) {
            onAnimationEndBeforeStart(info, info?.oldHolder)
            dispatchFinished(info, info?.oldHolder)
            info?.clear(info.oldHolder)
        }
        if ((info?.newHolder != null && viewHolder != null) || info?.newHolder == viewHolder) {
            onAnimationEndBeforeStart(info, info?.newHolder)
            dispatchFinished(info, info?.newHolder)
            info?.clear(info.newHolder)
        }

        return info?.oldHolder == null && info?.newHolder == null
    }

    abstract fun onCreateAnimationChangeForNewItem(info: AnimationChangeInfo)

    abstract fun onCreateAnimationChangeForOldItem(info: AnimationChangeInfo)

    abstract fun addPendingAnimation(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ):Boolean
}