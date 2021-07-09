package com.maoamade.powerfulrecy.animation.changeanimation

import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.info.AnimationRemoveInfo
import com.maoamade.powerfulrecy.animation.changeanimation.manager.ItemAnimationRemoveManager

/**

 * @Author maoamade

 * @Date 2021/7/9 5:11 PM

 * @Info SwipeDismissItemAnimator.kt

 */
class SwipeDismissItemAnimator : DraggableItemAnimator() {

    override fun onSetManager() {
        setAddManager(DefaultItemAddAnimationManager(this))
        setMoveManager(DefaultItemMoveAnimationManager(this))
        setRemoveManager(SwipeDismissItemRemoveAnimationManager(this))
        setChangeManager(DefaultItemChangeAnimationManager(this))
        removeDuration = 150
        moveDuration = 150
    }
}

class SwipeDismissItemRemoveAnimationManager(itemAnimator1: BaseItemAnimator):ItemAnimationRemoveManager(itemAnimator1){
    override fun addPendingAnimation(viewHolder: RecyclerView.ViewHolder?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onCreateAnimation(info: AnimationRemoveInfo?) {
        TODO("Not yet implemented")
    }

    override fun onAnimationCancel(info: AnimationRemoveInfo?, holder: RecyclerView.ViewHolder?) {
        TODO("Not yet implemented")
    }

    override fun onAnimationEndSuccess(
        info: AnimationRemoveInfo?,
        holder: RecyclerView.ViewHolder?
    ) {
        TODO("Not yet implemented")
    }

    override fun onAnimationEndBeforeStart(
        info: AnimationRemoveInfo?,
        holder: RecyclerView.ViewHolder?
    ) {
        TODO("Not yet implemented")
    }

}