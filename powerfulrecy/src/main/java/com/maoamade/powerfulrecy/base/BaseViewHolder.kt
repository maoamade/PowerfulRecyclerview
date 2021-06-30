package com.maoamade.powerfulrecy.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.listener.OnItemClickListener

/**

 * @Author maoamade

 * @Date 2021/6/30 2:51 PM

 * @Info BaseViewHolder.kt

 */
abstract class BaseViewHolder<T> : RecyclerView.ViewHolder {

    public var itemClickListener: OnItemClickListener<T>? = null

    constructor(itemView: View) : super(itemView) {
        this.initView(itemView)
    }

    constructor(viewGroup: ViewGroup, @LayoutRes layoutId: Int) : super(
        LayoutInflater.from(
            viewGroup.context
        ).inflate(layoutId, viewGroup, false)
    ) {
        this.initView(itemView)
    }

    abstract fun initView(view: View)

    abstract fun showView(data: T)

    public fun addItemListener(data: T){
        itemView.setOnClickListener {
            onItemClick(data,it)
            itemClickListener?.onItemClick(data,it,bindingAdapterPosition)
        }
    }

    /**
     * the ItemListener used in adapter
     */
    public fun addOnItemListener(itemClickListener: OnItemClickListener<T>?){
        this.itemClickListener = itemClickListener
    }

    /**
     * the ItemListener used in holder
     */
    open fun onItemClick(data: T,itemView: View){}
}