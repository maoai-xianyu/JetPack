package com.mao.jetpack.ui.text

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mao.jetpack.R
import com.mao.jetpack.databinding.ReboundItemViewBinding

/**
 *
 * @author zhangkun
 * @time 2023/3/22 16:06
 * @Description
 */
class ReboundAdapter : RecyclerView.Adapter<ReboundAdapter.ViewHolder>() {

    private val list = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = DataBindingUtil.inflate<ReboundItemViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.rebound_item_view, parent, false
        )

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.title = list[position]
        holder.binding.executePendingBindings()

    }

    override fun getItemCount(): Int = list.size


    fun refreshData(newData: List<String>) {
        list.clear()
        list.addAll(newData)
        this.notifyDataSetChanged()
    }

    class ViewHolder(var binding: ReboundItemViewBinding) : RecyclerView.ViewHolder(binding.root)
}