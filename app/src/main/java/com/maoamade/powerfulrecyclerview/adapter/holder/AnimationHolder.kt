package com.maoamade.powerfulrecyclerview.adapter.holder

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.maoamade.powerfulrecyclerview.R
import com.maoamade.powerfulrecyclerview.adapter.BaseViewHolder
import com.maoamade.powerfulrecyclerview.bean.DataEntity

class AnimationHolder(private val parent: ViewGroup) :
    BaseViewHolder<DataEntity>(parent, R.layout.item_animation) {

    private var tvContent: TextView? = null
    override fun initView(itemView: View) {
        tvContent = itemView.findViewById(R.id.tv_content)
    }

    override fun showView(data: DataEntity) {
        tvContent?.text = bindingAdapterPosition.toString() + "test"
    }
}