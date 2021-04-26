package com.why.cardlayoutmanager.touchhelper

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.why.cardlayoutmanager.LayoutManagerConfig
import com.why.cardlayoutmanager.adapter.LikeAndUnlikeAdapter
import com.why.cardlayoutmanager.adapter.LikeAndUnlikeBean
import kotlin.math.max
import kotlin.math.sqrt

/**
 * @ClassName: CardTouchHelper
 * @Description: java类作用描述
 * @Author: wanghaiyang91
 * @Date: 4/25/21 2:29 PM
 */

class CardTouchHelper(
    val adapter: LikeAndUnlikeAdapter,
    val mDatas: MutableList<LikeAndUnlikeBean>,
    val recyclerView: RecyclerView
) : ItemTouchHelper.SimpleCallback(
    //0表示哪个方向都没有拖动响应
    0,
    //15是滑动各个方向或完之后的值
    15
) {


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val bean = mDatas.removeAt(viewHolder.layoutPosition)
        mDatas.add(0, bean)
        adapter.notifyDataSetChanged()
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val maxDistance = recyclerView.width / 2
        val moveDistance = sqrt(dX * dX + dY * dY)
        var percent = moveDistance / maxDistance

        if(percent > 1){
            percent = 1f
        }

        for(item in 0 until recyclerView.childCount){
            val view = recyclerView.getChildAt(item)
            val level = LayoutManagerConfig.MAX_ITEM_SIZE - item - 1
            if(level > 0){
                if(LayoutManagerConfig.MAX_ITEM_SIZE - level > 1){
                    view.translationY = level * LayoutManagerConfig.TRANS_Y - percent * LayoutManagerConfig.TRANS_Y
                    view.scaleX = 1 - level * LayoutManagerConfig.SCALE + percent * LayoutManagerConfig.SCALE
                    view.scaleY = 1 - level * LayoutManagerConfig.SCALE + percent * LayoutManagerConfig.SCALE
                }
            }
        }

    }

    override fun getAnimationDuration(
        recyclerView: RecyclerView,
        animationType: Int,
        animateDx: Float,
        animateDy: Float
    ): Long {

        //修改card消失或者恢复的动画时间
        return 500
    }

}