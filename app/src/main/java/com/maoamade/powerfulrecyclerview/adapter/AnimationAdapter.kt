package com.maoamade.powerfulrecyclerview.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.base.BaseAdapter
import com.maoamade.powerfulrecy.base.BaseViewHolder
import com.maoamade.powerfulrecyclerview.adapter.holder.AnimationHolder
import com.maoamade.powerfulrecyclerview.bean.DataEntity

/**

 * @Author maoamade

 * @Date 2021/6/29 4:24 PM

 * @Info AnimationAdapter.kt

 */
class AnimationAdapter(context: Context) : BaseAdapter<DataEntity>(context){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DataEntity> {
        return AnimationHolder(parent)
    }
}