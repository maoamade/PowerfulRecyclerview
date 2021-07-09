package com.maoamade.powerfulrecy.animation.changeanimation.info

import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.changeanimation.ItemAnimationInfo

/**

 * @Author maoamade

 * @Date 2021/7/7 4:53 PM

 * @Info AnimationAddInfo.kt

 */
class AnimationAddInfo(var holder: RecyclerView.ViewHolder?) : ItemAnimationInfo() {

    override fun getAvailableViewHolder(): RecyclerView.ViewHolder? = this.holder

    override fun clear(holder: RecyclerView.ViewHolder?) {
        if (this.holder == holder){
            this.holder = null
        }
    }
}