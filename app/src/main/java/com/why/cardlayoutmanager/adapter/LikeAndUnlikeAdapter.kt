package com.why.cardlayoutmanager.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.why.cardlayoutmanager.R

/**
 * @ClassName: LikeAndUnlikeAdapter
 * @Description: java类作用描述
 * @Author: wanghaiyang91
 * @Date: 4/20/21 11:42 AM
 */
class LikeAndUnlikeAdapter : RecyclerView.Adapter<LikeAndUnlikViewHolder>() {

    val datas = mutableListOf<LikeAndUnlikeBean>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeAndUnlikViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.like_and_unlike_item_layout, parent, false)
        return LikeAndUnlikViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikeAndUnlikViewHolder, position: Int) {
        holder.bindView(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    fun updateDatas(newDatas: MutableList<LikeAndUnlikeBean>){
        this.datas.apply {
            clear()
            addAll(newDatas)
        }
        notifyDataSetChanged()
    }
}

class LikeAndUnlikViewHolder constructor(val root: View) : RecyclerView.ViewHolder(root){

    val text = root.findViewById<TextView>(R.id.text)
    val imge = root.findViewById<ImageView>(R.id.imge)

    fun bindView(bean: LikeAndUnlikeBean){
        bean.apply {
            text.text = name
            imge.load(img){
                crossfade(true)
                placeholder(R.mipmap.ic_launcher)
            }
        }
    }
}

data class LikeAndUnlikeBean(val img: String, val name: String)