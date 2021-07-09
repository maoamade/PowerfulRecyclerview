package com.maoamade.powerfulrecy.animation.changeanimation.manager

import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.BaseItemAnimator
import com.maoamade.powerfulrecy.animation.changeanimation.info.AnimationRemoveInfo

/**

 * @Author maoamade

 * @Date 2021/7/8 4:22 PM

 * @Info ItemAnimationRemoveManager.kt

 */
abstract class ItemAnimationRemoveManager :BaseItemAnimationManager<AnimationRemoveInfo>{

    constructor(itemAnimator: BaseItemAnimator) {
        this.itemAnimator = itemAnimator
    }

    override fun dispatchStarting(
        info: AnimationRemoveInfo?,
        viewHolder: RecyclerView.ViewHolder?
    ) {
        itemAnimator.dispatchRemoveStarting(viewHolder)
    }

    override fun dispatchFinished(
        info: AnimationRemoveInfo?,
        viewHolder: RecyclerView.ViewHolder?
    ) {
        itemAnimator.dispatchRemoveFinished(viewHolder)
    }

    override fun getDuration(): Long = itemAnimator.removeDuration

    override fun setDuration(duration: Long) {
        itemAnimator.removeDuration = duration
    }

    override fun endNotStartedAnimation(
        info: AnimationRemoveInfo?,
        viewHolder: RecyclerView.ViewHolder?
    ): Boolean {
        return if ((info?.holder != null && viewHolder != null) || info?.holder == viewHolder){
            onAnimationEndBeforeStart(info,info?.holder)
            dispatchFinished(info,info?.holder)
            info?.clear(info.holder)
            true
        }else{
            false
        }
    }

    abstract fun addPendingAnimation(viewHolder: RecyclerView.ViewHolder?):Boolean
}