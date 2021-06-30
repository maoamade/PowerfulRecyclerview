package com.maoamade.powerfulrecy.listener

import android.view.View

/**

 * @Author maoamade

 * @Date 2021/6/30 2:51 PM

 * @Info OnItemClickListener.kt

 */
interface OnItemClickListener<T> {

    fun onItemClick(data: T, itemView: View, position: Int)

}