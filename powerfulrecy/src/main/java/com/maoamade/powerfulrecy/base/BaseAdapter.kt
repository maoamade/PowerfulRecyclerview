package com.maoamade.powerfulrecy.base

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.entranceanimation.BaseEntranceAnimation
import com.maoamade.powerfulrecy.animation.entranceanimation.*
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

    private var adapterEntranceAnimation: BaseEntranceAnimation? = null

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
            val animation = adapterEntranceAnimation?.animators(holder.itemView)
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
        adapterEntranceAnimation = when(type){
            AnimationType.ANIMATION_ALPHA -> AlphaInEntranceAnimation()
            AnimationType.ANIMATION_SCALE -> ScaleInEntranceAnimation()
            AnimationType.ANIMATION_SLIDE_BOTTOM -> SlideInBottomEntranceAnimation()
            AnimationType.ANIMATION_SLIDE_RIGHT -> SlideInRightEntranceAnimation()
            AnimationType.ANIMATION_SLIDE_LEFT -> SlideInLeftEntranceAnimation()
        }
    }

    /**
     * set the customize animation when the item is added to the window
     */
    fun setAdapterAnimation(adapterEntranceAnimation: BaseEntranceAnimation?){
        this.adapterEntranceAnimation = adapterEntranceAnimation
    }

}

enum class AnimationType {
    ANIMATION_ALPHA, ANIMATION_SCALE, ANIMATION_SLIDE_BOTTOM, ANIMATION_SLIDE_RIGHT, ANIMATION_SLIDE_LEFT
}