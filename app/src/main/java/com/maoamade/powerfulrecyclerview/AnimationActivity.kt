package com.maoamade.powerfulrecyclerview

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maoamade.powerfulrecyclerview.adapter.AnimationAdapter
import com.maoamade.powerfulrecyclerview.adapter.BaseAdapter
import com.maoamade.powerfulrecyclerview.bean.DataEntity

/**

 * @Author maoamade

 * @Date 2021/6/29 4:16 PM

 * @Info AnimationActivity.kt

 */
class AnimationActivity : AppCompatActivity() {

    private var recyContent: RecyclerView? = null
    private var mAdapter : AnimationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        recyContent = findViewById(R.id.recy_content)
        mAdapter = AnimationAdapter(this)
        recyContent?.apply {
            layoutManager = LinearLayoutManager(this@AnimationActivity,RecyclerView.VERTICAL,false)
            adapter = mAdapter

        }
        mAdapter?.setAnimationWithDefault(BaseAdapter.AnimationType.ScaleIn)
        initData()
    }

    private fun initData() {
        val dataList:ArrayList<DataEntity> = arrayListOf()
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        dataList.add(DataEntity("1","1"))
        mAdapter?.addData(dataList)
    }
}