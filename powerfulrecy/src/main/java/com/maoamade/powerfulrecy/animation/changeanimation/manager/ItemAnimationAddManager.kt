package com.maoamade.powerfulrecy.animation.changeanimation.manager

import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.BaseItemAnimator
import com.maoamade.powerfulrecy.animation.changeanimation.info.AnimationAddInfo

/**

 * @Author maoamade

 * @Date 2021/7/7 4:52 PM

 * @Info ItemAnimationAddManager.kt

 */
abstract class ItemAnimationAddManager(itemAnimator1: BaseItemAnimator) : BaseItemAnimationManager<AnimationAddInfo>(itemAnimator1) {

    override fun setDuration(duration: Long) {
        itemAnimator.addDuration = duration
    }

    override fun getDuration(): Long = if (itemAnimator.changeDuration >0) itemAnimator.changeDuration else 3000

    override fun dispatchStarting(info: AnimationAddInfo?, viewHolder: RecyclerView.ViewHolder?) {
        itemAnimator.dispatchAddStarting(viewHolder)
    }

    override fun dispatchFinished(info: AnimationAddInfo?, viewHolder: RecyclerView.ViewHolder?) {
        itemAnimator.dispatchAddFinished(viewHolder)
    }

    override fun endNotStartedAnimation(
        info: AnimationAddInfo?,
        viewHolder: RecyclerView.ViewHolder?
    ): Boolean {
        return if ((info?.holder != null && viewHolder != null) || info?.holder == viewHolder) {
            onAnimationEndBeforeStart(info,viewHolder)
            dispatchFinished(info,info?.holder)
            info?.clear(info.holder)
            true
        }else{
            false
        }
    }

    abstract fun addPendingAnimation(holder: RecyclerView.ViewHolder?):Boolean
}