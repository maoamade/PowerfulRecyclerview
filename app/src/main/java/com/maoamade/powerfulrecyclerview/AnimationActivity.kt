package com.maoamade.powerfulrecyclerview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecy.animation.ItemAnimation.FadeInAnimator
import com.maoamade.powerfulrecy.animation.changeanimation.RefactoredDefaultItemAnimator
import com.maoamade.powerfulrecy.base.AnimationType
import com.maoamade.powerfulrecyclerview.adapter.AnimationAdapter
import com.maoamade.powerfulrecyclerview.bean.DataEntity

/**

 * @Author maoamade

 * @Date 2021/6/29 4:16 PM

 * @Info AnimationActivity.kt

 */
class AnimationActivity : AppCompatActivity() {

    private var recyContent: RecyclerView? = null
    private var mAdapter: AnimationAdapter? = null
    private var mAdd: TextView? = null
    private var mDelete: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        mAdd = findViewById(R.id.add)
        mDelete = findViewById(R.id.delete)
        recyContent = findViewById(R.id.recy_content)
        mAdapter = AnimationAdapter(this)
        val animator = RefactoredDefaultItemAnimator()
        animator.supportsChangeAnimations = false
        recyContent?.apply {
            layoutManager =
                LinearLayoutManager(this@AnimationActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
            itemAnimator = FadeInAnimator()
        }

        // Change animations are enabled by default since support-v7-recyclerview v22.
        // Need to disable them when using animation indicator.

        mAdapter?.setAnimationAvailable(true)
        mAdapter?.setAdapterAnimation(AnimationType.ANIMATION_SCALE)
        initData()

        mAdd?.setOnClickListener {
            mAdapter?.addData(DataEntity("1", "1"),2)
        }
        mDelete?.setOnClickListener {
            mAdapter?.remove(1)
        }
    }

    private fun initData() {
        val dataList: ArrayList<DataEntity> = arrayListOf()
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        dataList.add(DataEntity("1", "1"))
        mAdapter?.addData(dataList)
    }
}