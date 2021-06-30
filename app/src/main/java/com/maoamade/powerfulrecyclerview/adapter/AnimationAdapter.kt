package com.maoamade.powerfulrecyclerview.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecyclerview.adapter.holder.AnimationHolder
import com.maoamade.powerfulrecyclerview.bean.DataEntity

/**

 * @Author maoamade

 * @Date 2021/6/29 4:24 PM

 * @Info AnimationAdapter.kt

 */
class AnimationAdapter(private val context: Context) :BaseAdapter<DataEntity>(context){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DataEntity> {
        return AnimationHolder(parent)
    }
}