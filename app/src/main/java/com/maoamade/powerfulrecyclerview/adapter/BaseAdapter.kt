package com.maoamade.powerfulrecyclerview.adapter

import android.animation.Animator
import android.content.Context
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.BaseAnimation
import com.maoamade.powerfulrecy.animation.baseanimation.*
import com.maoamade.powerfulrecyclerview.listener.OnItemClickListener

/**
 * <pre>
 *     time   : 2020-12-07
 *     desc   : BaseAdapter
 *     version: 1.0
 * </pre>
 */
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>> {

     var mData: ArrayList<T>

    private var mContext: Context

    var adapterAnimation :BaseAnimation? = null


    protected var itemClickListener: OnItemClickListener<T>? = null
    private var mLastPosition = -1


    constructor(context: Context) {
        this.mContext = context
        mData = ArrayList()
    }


    public fun getAllData(): List<T> = mData


    open fun addData(list: List<T>) {
        mData.addAll(list)
        notifyDataSetChanged()
    }

    public fun addOneData(data: T) {
        mData.add(data)
    }


    public fun clear() {
        mData.clear()
        notifyDataSetChanged()
    }


    open fun getItemData(position: Int): T {
        return mData[position]
    }


    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {

        val data = getItemData(position)
        holder.showView(getItemData(position))
        holder.addItemClickListener(itemClickListener)
        holder.setListener(data, position)


    }

    open fun setViewItemClickListener(itemClickListener: OnItemClickListener<T>) {
        this.itemClickListener = itemClickListener
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<T>) {
        super.onViewAttachedToWindow(holder)
        addAnimation(holder)
    }

    private fun addAnimation(holder: BaseViewHolder<T>) {
        if (holder.layoutPosition > mLastPosition) {
            val animation: BaseAnimation = adapterAnimation ?: AlphaInAnimation()
            animation.animators(holder.itemView).forEach {
                startAnim(it, holder.layoutPosition)
            }
            mLastPosition = holder.layoutPosition
        }
    }

    private fun startAnim(it: Animator, layoutPosition: Int) {
        it.start()
    }

    /**
     * 内置默认动画类型
     */
    enum class AnimationType {
        AlphaIn, ScaleIn, SlideInBottom, SlideInLeft, SlideInRight
    }

    /**
     * 使用内置默认动画设置
     * @param animationType AnimationType
     */
    fun setAnimationWithDefault(animationType: AnimationType) {
        adapterAnimation = when (animationType) {
            AnimationType.AlphaIn -> AlphaInAnimation()
            AnimationType.ScaleIn -> ScaleInAnimation()
            AnimationType.SlideInBottom -> SlideInBottomAnimation()
            AnimationType.SlideInLeft -> SlideInLeftAnimation()
            AnimationType.SlideInRight -> SlideInRightAnimation()
        }
    }


}