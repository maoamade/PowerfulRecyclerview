package com.maoamade.powerfulrecy.animation.changeanimation.info

import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.ItemAnimationInfo

/**

 * @Author maoamade

 * @Date 2021/7/8 10:51 AM

 * @Info AnimationChangeInfo.kt

 */
class AnimationChangeInfo(
    var oldHolder: RecyclerView.ViewHolder?,
    var newHolder: RecyclerView.ViewHolder?,
    var fromX: Int,
    var fromY: Int,
    var toX: Int,
    var toY: Int
) : ItemAnimationInfo() {

    override fun getAvailableViewHolder(): RecyclerView.ViewHolder? {
        return oldHolder ?: newHolder
    }

    override fun clear(holder: RecyclerView.ViewHolder?) {
        if (oldHolder == holder){
            oldHolder = null
        }
        if (newHolder == holder){
            newHolder = null
        }
        if (oldHolder == null && newHolder == null){
            fromX = 0
            fromY = 0
            toX = 0
            toY = 0
        }
    }

}