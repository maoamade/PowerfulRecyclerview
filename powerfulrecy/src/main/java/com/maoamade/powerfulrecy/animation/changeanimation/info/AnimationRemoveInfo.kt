package com.maoamade.powerfulrecy.animation.changeanimation.info

import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.ItemAnimationInfo

/**

 * @Author maoamade

 * @Date 2021/7/8 3:21 PM

 * @Info AnimationRemoveInfo.kt

 */
class AnimationRemoveInfo(var holder: RecyclerView.ViewHolder?) : ItemAnimationInfo() {

    override fun getAvailableViewHolder(): RecyclerView.ViewHolder?  = holder

    override fun clear(holder: RecyclerView.ViewHolder?) {
        if (this.holder == holder){
            this.holder = null
        }
    }
}