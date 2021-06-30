package com.maoamade.powerfulrecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecyclerview.listener.OnItemClickListener

/**
 * <pre>
 *     time   : 2019-12-07
 *     desc   : BaseViewHolder
 *     version: 1.0
 * </pre>
 */
abstract class BaseViewHolder<T> : RecyclerView.ViewHolder {


    private var mNeedItemClick: Boolean = false
    private var mViewClick: Boolean = false
    public var itemClickListener: OnItemClickListener<T>? = null

    constructor(itemView: View) : super(itemView) {
        this.initView(itemView)
    }

    constructor(viewGroup: ViewGroup, @LayoutRes res: Int) : super(
        LayoutInflater.from(viewGroup.context).inflate(
            res,
            viewGroup,
            false
        )
    ) {
        this.initView(itemView)
    }

    abstract fun initView(itemView: View)

    abstract fun showView(data: T)

    fun getContext(): Context {
        return itemView.context
    }


    public fun setListener(data: T, position: Int) {


        if (mViewClick) return

        itemView.setOnClickListener {
            if (mNeedItemClick && itemClickListener != null) {
                itemClickListener?.onItemClick(data, it, position)
            } else {
                onItemClick(data, it, position)
            }
        }


    }

    protected open fun onItemClick(data: T, itemView: View, position: Int) {

    }

    public fun addItemClickListener(itemClickListener: OnItemClickListener<T>?) {
        this.itemClickListener = itemClickListener
    }


    protected open fun setNeedItemClick(need: Boolean) {
        this.mNeedItemClick = need
    }


    protected open fun setViewClick(need: Boolean) {
        this.mViewClick = need
    }

}