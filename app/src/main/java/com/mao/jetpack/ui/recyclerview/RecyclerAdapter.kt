package com.mao.jetpack.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mao.jetpack.databinding.ActivityRecyclerItemBinding

/**
 *
 * @author zhangkun
 * @time 2023/11/30 10:50
 * @Description
 */
class RecyclerAdapter(private val imageList: List<Int>) :
    RecyclerView.Adapter<RecyclerAdapter.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {

        val inflate =
            ActivityRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(inflate)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(imageList[position])

    }

    override fun getItemCount() = if (imageList.isEmpty()) {
        0
    } else {
        imageList.size
    }

    class Holder(private val binding: ActivityRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imageRes: Int) {
            binding.iv.setImageResource(imageRes)
        }

    }
}