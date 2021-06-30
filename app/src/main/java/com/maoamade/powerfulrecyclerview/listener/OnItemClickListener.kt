package com.maoamade.powerfulrecyclerview.listener

import android.view.View

/**
 * <pre>
 *     time   : 2020-12-07
 *     desc   : OnItemClickListener
 *     version: 1.0
 * </pre>
 */
interface OnItemClickListener<T> {

    fun onItemClick(data: T, itemView: View, position: Int)
}