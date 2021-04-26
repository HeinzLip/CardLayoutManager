package com.why.cardlayoutmanager.layoutmanager

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.why.cardlayoutmanager.LayoutManagerConfig
import com.why.cardlayoutmanager.LayoutManagerConfig.Companion.SCALE

/**
 * @ClassName: CardLayoutManager
 * @Description: java类作用描述
 * @Author: wanghaiyang91
 * @Date: 4/25/21 11:01 AM
 */
class CardLayoutManager : RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams = RecyclerView.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        detachAndScrapAttachedViews(recycler!!)

        val bottomPosition = if(itemCount > LayoutManagerConfig.MAX_ITEM_SIZE) itemCount - LayoutManagerConfig.MAX_ITEM_SIZE else 0

        for(item in bottomPosition until itemCount){
            val view = recycler.getViewForPosition(item)
            addView(view)
            measureChild(view, 0, 0)
            val gapWith = width - getDecoratedMeasuredWidth(view)
            val gapHeight = height - getDecoratedMeasuredHeight(view)
            layoutDecoratedWithMargins(view, gapWith / 2, gapHeight / 2,
            gapWith / 2 + getDecoratedMeasuredWidth(view), gapHeight / 2 + getDecoratedMeasuredHeight(view))

            val level = itemCount - item - 1
            if(level > 0){

                if(LayoutManagerConfig.MAX_ITEM_SIZE - level > 1){
                    view.translationY = level * LayoutManagerConfig.TRANS_Y
                    view.scaleX = (1 - level * SCALE)
                    view.scaleY = (1 - level * SCALE)
                } else {
                    view.translationY = (level - 1) * LayoutManagerConfig.TRANS_Y
                    view.scaleX = (1 - (level - 1) * SCALE)
                    view.scaleY = (1 - (level - 1) * SCALE)
                }

            }
        }
    }
}