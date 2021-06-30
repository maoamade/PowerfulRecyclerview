package com.maoamade.powerfulrecy

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class PowerfulRecyclerview : RecyclerView{

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}