package com.maoamade.powerfulrecy.animation.changeanimation

import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.info.AnimationAddInfo
import com.maoamade.powerfulrecy.animation.changeanimation.info.AnimationChangeInfo
import com.maoamade.powerfulrecy.animation.changeanimation.info.AnimationMoveInfo
import com.maoamade.powerfulrecy.animation.changeanimation.info.AnimationRemoveInfo
import com.maoamade.powerfulrecy.animation.changeanimation.manager.ItemAnimationAddManager
import com.maoamade.powerfulrecy.animation.changeanimation.manager.ItemAnimationChangeManager
import com.maoamade.powerfulrecy.animation.changeanimation.manager.ItemAnimationMoveManager
import com.maoamade.powerfulrecy.animation.changeanimation.manager.ItemAnimationRemoveManager

/**

 * @Author maoamade

 * @Date 2021/7/9 3:12 PM

 * @Info RefactoredDefaultItemAnimator.kt

 */
open class RefactoredDefaultItemAnimator : GeneralItemAnimator() {

    override fun onSetManager() {
        setAddManager(DefaultItemAddAnimationManager(this))
        setChangeManager(DefaultItemChangeAnimationManager(this))
        setMoveManager(DefaultItemMoveAnimationManager(this))
        setRemoveManager(DefaultItemRemoveAnimationManager(this))
    }

    override fun onSchedulePendingAnimations() {
        super.onSchedulePendingAnimations()
    }


    override fun canReuseUpdatedViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ): Boolean {
        return payloads.isNotEmpty() || super.canReuseUpdatedViewHolder(viewHolder, payloads)
    }
}

class DefaultItemAddAnimationManager(itemAnimator1: BaseItemAnimator) : ItemAnimationAddManager(itemAnimator1) {

    override fun addPendingAnimation(holder: RecyclerView.ViewHolder?): Boolean {
        resetAnimation(holder)
        holder?.itemView?.alpha = 0f
        enqueuePendingAnimationInfo(AnimationAddInfo(holder))
        return true
    }

    override fun onCreateAnimation(info: AnimationAddInfo?) {
        info?.holder?.itemView?.let {
            val animation = ViewCompat.animate(it)
            animation.duration = getDuration()
            animation.alpha(1f)
            startActiveItemAnimaiton(info, info.holder, animation)
        }
    }

    override fun onAnimationCancel(info: AnimationAddInfo?, holder: RecyclerView.ViewHolder?) {
        holder?.itemView?.alpha = 1f
    }

    override fun onAnimationEndSuccess(info: AnimationAddInfo?, holder: RecyclerView.ViewHolder?) {
    }

    override fun onAnimationEndBeforeStart(
        info: AnimationAddInfo?,
        holder: RecyclerView.ViewHolder?
    ) {
        holder?.itemView?.alpha = 1f
    }

}

class DefaultItemChangeAnimationManager(itemAnimator1: BaseItemAnimator) : ItemAnimationChangeManager(itemAnimator1) {

    // new appear
    override fun onCreateAnimationChangeForNewItem(info: AnimationChangeInfo) {
        info.newHolder?.itemView?.let {
            val newAnimator = ViewCompat.animate(it)
            newAnimator.duration = getDuration()
            newAnimator.translationX(0f)
            newAnimator.translationY(0f)
            newAnimator.alpha(1f)
            startActiveItemAnimaiton(info, info.newHolder, newAnimator)
        }
    }

    // old disappear
    override fun onCreateAnimationChangeForOldItem(info: AnimationChangeInfo) {
        info.oldHolder?.itemView?.let {
            val oldAnimator = ViewCompat.animate(it)
            oldAnimator.duration = getDuration()
            oldAnimator.translationX((info.toX - info.fromX).toFloat())
            oldAnimator.translationY((info.toY - info.fromY).toFloat())
            oldAnimator.alpha(0f)
            startActiveItemAnimaiton(info, info.oldHolder, oldAnimator)
        }
    }

    override fun addPendingAnimation(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {
        val oldTranslateX = oldHolder?.itemView?.translationX ?: 0f
        val oldTranslateY = oldHolder?.itemView?.translationY ?: 0f
        val oldAlpha = oldHolder?.itemView?.alpha ?: 0f
        resetAnimation(oldHolder)
        oldHolder?.itemView?.translationX = oldTranslateX
        oldHolder?.itemView?.translationY = oldTranslateY
        oldHolder?.itemView?.alpha = oldAlpha

        resetAnimation(newHolder)

        val deltaX = toX - fromX - oldTranslateX
        val deltaY = toY - fromY - oldTranslateY

        newHolder?.itemView?.translationX = -deltaX
        newHolder?.itemView?.translationY = -deltaY
        newHolder?.itemView?.alpha = 0f
        enqueuePendingAnimationInfo(
            AnimationChangeInfo(
                oldHolder,
                newHolder,
                fromX,
                fromY,
                toX,
                toY
            )
        )
        return true
    }

    override fun onAnimationCancel(info: AnimationChangeInfo?, holder: RecyclerView.ViewHolder?) {
    }

    override fun onAnimationEndSuccess(
        info: AnimationChangeInfo?,
        holder: RecyclerView.ViewHolder?
    ) {
        holder?.itemView?.alpha = 1f
        holder?.itemView?.translationX = 0f
        holder?.itemView?.translationY = 0f
    }

    override fun onAnimationEndBeforeStart(
        info: AnimationChangeInfo?,
        holder: RecyclerView.ViewHolder?
    ) {
        holder?.itemView?.alpha = 1f
        holder?.itemView?.translationX = 0f
        holder?.itemView?.translationY = 0f
    }

}

class DefaultItemMoveAnimationManager(itemAnimator1: BaseItemAnimator) : ItemAnimationMoveManager(itemAnimator1) {

    override fun addPendingAnimaiton(
        holder: RecyclerView.ViewHolder?,
        fromX: Int,
        fromY: Int,
        toX: Int,
        toY: Int
    ): Boolean {
        val changeX = fromX + (holder?.itemView?.translationX?.toInt() ?: 0)
        val changeY = fromY + (holder?.itemView?.translationY?.toInt() ?: 0)
        resetAnimation(holder)
        val deltaX = toX - changeX
        val deltaY = toY - changeY
        val moveInfo = AnimationMoveInfo(holder, fromX, fromY, toX, toY)
        if (deltaX == 0 && deltaY == 0) {
            dispatchFinished(moveInfo, holder)
            moveInfo.clear(holder)
            return false
        }
        holder?.itemView?.translationX = deltaX.toFloat()
        holder?.itemView?.translationY = deltaY.toFloat()
        enqueuePendingAnimationInfo(moveInfo)
        return true
    }

    override fun onCreateAnimation(info: AnimationMoveInfo?) {
        info?.let {
            val deltaX = it.toX - it.fromX
            val deltaY = it.toY - it.fromY

            it.mHolder?.let { holder ->
                if (deltaX != 0) {
                    ViewCompat.animate(holder.itemView).translationX(0f)
                }
                if (deltaY != 0) {
                    ViewCompat.animate(holder.itemView).translationY(0f)
                }
                val animator = ViewCompat.animate(holder.itemView)
                animator.duration = getDuration()
                startActiveItemAnimaiton(info, holder, animator)
            }
        }
    }

    override fun onAnimationCancel(info: AnimationMoveInfo?, holder: RecyclerView.ViewHolder?) {
        holder?.let {
            val deltaX = (info?.toX ?: 0) - (info?.fromX ?: 0)
            val deltaY = (info?.toY ?: 0) - (info?.fromY ?: 0)
            if (deltaX != 0) {
                it.itemView.translationX = 0f
                ViewCompat.animate(it.itemView).translationX(0f)
            }
            if (deltaY != 0) {
                it.itemView.translationY = 0f
                ViewCompat.animate(it.itemView).translationY(0f)
            }
        }
    }

    override fun onAnimationEndSuccess(info: AnimationMoveInfo?, holder: RecyclerView.ViewHolder?) {
    }

    override fun onAnimationEndBeforeStart(
        info: AnimationMoveInfo?,
        holder: RecyclerView.ViewHolder?
    ) {
        holder?.itemView?.translationX = 0f
        holder?.itemView?.translationY = 0f

    }
}

class DefaultItemRemoveAnimationManager(itemAnimator1: BaseItemAnimator) : ItemAnimationRemoveManager(itemAnimator1){

    override fun addPendingAnimation(viewHolder: RecyclerView.ViewHolder?): Boolean {
        resetAnimation(viewHolder)
        enqueuePendingAnimationInfo(AnimationRemoveInfo(viewHolder))
        return true
    }

    override fun onCreateAnimation(info: AnimationRemoveInfo?) {
        info?.holder?.itemView?.let { itemView ->
            val animator = ViewCompat.animate(itemView)
            animator.duration = getDuration()
            animator.alpha(0f)
            startActiveItemAnimaiton(info,info.holder,animator)
        }
    }

    override fun onAnimationCancel(info: AnimationRemoveInfo?, holder: RecyclerView.ViewHolder?) {
    }

    override fun onAnimationEndSuccess(
        info: AnimationRemoveInfo?,
        holder: RecyclerView.ViewHolder?
    ) {
        holder?.itemView?.alpha = 1f
    }

    override fun onAnimationEndBeforeStart(
        info: AnimationRemoveInfo?,
        holder: RecyclerView.ViewHolder?
    ) {
        holder?.itemView?.alpha = 1f
    }

}
