package com.maoamade.powerfulrecy.animation.changeanimation

import androidx.recyclerview.widget.RecyclerView

/**

 * @Author maoamade

 * @Date 2021/7/9 5:06 PM

 * @Info DraggableItemAnimator.kt

 */
open class DraggableItemAnimator : RefactoredDefaultItemAnimator() {
    override fun onSetManager() {
        super.onSetManager()
        super.setSupportsChangeAnimations(false)
    }

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder?,
        newHolder: RecyclerView.ViewHolder?,
        fromLeft: Int,
        fromTop: Int,
        toLeft: Int,
        toTop: Int
    ): Boolean {
        if (oldHolder == newHolder && fromLeft == toLeft && fromTop == toTop) {
            dispatchChangeFinished(oldHolder, true)
        }
        return super.animateChange(oldHolder, newHolder, fromLeft, fromTop, toLeft, toTop)
    }
}