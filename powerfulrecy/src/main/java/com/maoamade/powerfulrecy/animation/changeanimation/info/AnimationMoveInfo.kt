package com.maoamade.powerfulrecy.animation.changeanimation.info

import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.ItemAnimationInfo

/**

 * @Author maoamade

 * @Date 2021/7/8 3:16 PM

 * @Info AnimationMoveInfo.kt

 */
class AnimationMoveInfo(
    var mHolder: RecyclerView.ViewHolder?,
    var fromX: Int,
    var fromY: Int,
    var toX: Int,
    var toY: Int
) : ItemAnimationInfo() {

    override fun getAvailableViewHolder(): RecyclerView.ViewHolder?  = mHolder

    override fun clear(holder: RecyclerView.ViewHolder?) {
        if (mHolder == holder){
            mHolder = null
        }
    }
}