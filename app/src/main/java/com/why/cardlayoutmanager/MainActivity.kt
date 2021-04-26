package com.why.cardlayoutmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import com.why.cardlayoutmanager.adapter.LikeAndUnlikeAdapter
import com.why.cardlayoutmanager.adapter.LikeAndUnlikeBean
import com.why.cardlayoutmanager.layoutmanager.CardLayoutManager
import com.why.cardlayoutmanager.touchhelper.CardTouchHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val adapter = LikeAndUnlikeAdapter();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    private fun initView(){
        recycler.adapter = adapter
        recycler.layoutManager = CardLayoutManager()
        val callBack = CardTouchHelper(adapter, adapter.datas, recycler)
        val helper = ItemTouchHelper(callBack)
        helper.attachToRecyclerView(recycler)
//        recycler.layoutManager = LinearLayoutManager(context)
    }

    private fun initData(){
        val datas = mutableListOf<LikeAndUnlikeBean>().apply {
            add(LikeAndUnlikeBean("http://pic.5tu.cn/uploads/allimg/2103/pic_5tu_big_6142732_e0fafeee8063b37437c13a76a9f19a95.jpg", "aaa"))
            add(LikeAndUnlikeBean("http://pic.5tu.cn/uploads/allimg/2103/pic_5tu_big_6142732_f27f3143529c16705693296a2b5f6756.jpg", "aaa"))
            add(LikeAndUnlikeBean("http://pic.5tu.cn/uploads/allimg/2104/pic_5tu_big_202104141615371418.jpg", "aaa"))
            add(LikeAndUnlikeBean("http://pic.5tu.cn/uploads/allimg/2104/pic_5tu_big_6142732_4882dd5c481db0ba507e5a00d6270395.jpg", "aaa"))
            add(LikeAndUnlikeBean("http://pic.5tu.cn/uploads/allimg/2104/pic_5tu_big_202103121347234437.jpg", "aaa"))
        }
        adapter.updateDatas(datas)
    }
}