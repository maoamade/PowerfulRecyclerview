package com.maoamade.powerfulrecy.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.BaseAnimation
import com.maoamade.powerfulrecy.animation.baseanimation.*
import com.maoamade.powerfulrecy.listener.OnItemClickListener

/**

 * @Author maoamade

 * @Date 2021/6/30 2:51 PM

 * @Info BaseAdapter.kt

 */
abstract class BaseAdapter<T>(val context: Context) :
    RecyclerView.Adapter<BaseViewHolder<T>>() {

    var dataList: ArrayList<T> = arrayListOf()

    var itemClickListener: OnItemClickListener<T>? = null

    private var adapterAnimationEnable: Boolean = false

    private var adapterAnimation: BaseAnimation? = null

    private var mLastPosition = -1

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.showView(getItemData(position))
        holder.addItemListener(getItemData(position))
        holder.addOnItemListener(itemClickListener)
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<T>) {
        super.onViewAttachedToWindow(holder)
        addAnimation(holder)
    }

    /**
     * add the animation when the item is added to the window
     */
    private fun addAnimation(holder: BaseViewHolder<T>) {
        if (adapterAnimationEnable && holder.layoutPosition > mLastPosition){
            val animation = adapterAnimation?.animators(holder.itemView)
            animation?.forEach {
                it.start()
            }
            mLastPosition = holder.layoutPosition
        }
    }

    /**
     * getItemData
     */
    fun getItemData(position: Int): T {
        return dataList[position]
    }

    /**
     * addAllData
     */
    fun addData(dataList: ArrayList<T>) {
        this.dataList.addAll(dataList)
        notifyDataSetChanged()
    }

    /**
     * add single data
     */
    fun addData(data: T) {
        this.dataList.add(data)
        notifyDataSetChanged()
    }

    /**
     * clear
     */
    fun clear() {
        this.dataList.clear()
        notifyDataSetChanged()
    }

    /**
     * set the animation when the item is added to the window is Available
     */
    fun setAnimationAvailable(adapterAnimationEnable: Boolean) {
        this.adapterAnimationEnable = adapterAnimationEnable
    }

    /**
     * set the type of animation when the item is added to the window
     */
    fun setAdapterAnimation(type: AnimationType) {
        adapterAnimation = when(type){
            AnimationType.ANIMATION_ALPHA -> AlphaInAnimation()
            AnimationType.ANIMATION_SCALE -> ScaleInAnimation()
            AnimationType.ANIMATION_SLIDE_BOTTOM -> SlideInBottomAnimation()
            AnimationType.ANIMATION_SLIDE_RIGHT -> SlideInRightAnimation()
            AnimationType.ANIMATION_SLIDE_LEFT -> SlideInLeftAnimation()
        }
    }

    /**
     * set the customize animation when the item is added to the window
     */
    fun setAdapterAnimation(adapterAnimation: BaseAnimation?){
        this.adapterAnimation = adapterAnimation
    }

}

enum class AnimationType {
    ANIMATION_ALPHA, ANIMATION_SCALE, ANIMATION_SLIDE_BOTTOM, ANIMATION_SLIDE_RIGHT, ANIMATION_SLIDE_LEFT
}