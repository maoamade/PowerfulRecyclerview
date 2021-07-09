package com.maoamade.powerfulrecy.animation.changeanimation

import androidx.recyclerview.widget.RecyclerView

/**

 * @Author maoamade

 * @Date 2021/7/6 4:05 PM

 * @Info ItemAnimationInfo.kt

 */
abstract class ItemAnimationInfo {
    abstract fun getAvailableViewHolder():RecyclerView.ViewHolder?
    abstract fun clear(holder: RecyclerView.ViewHolder?)
}