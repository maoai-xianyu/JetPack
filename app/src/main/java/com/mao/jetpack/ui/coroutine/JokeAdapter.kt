package com.mao.jetpack.ui.coroutine

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mao.jetpack.R
import com.mao.jetpack.databinding.ActivityCoroutineAdapterItemBinding
import com.mao.jetpack.model.DataModel

/**
 *
 * @author zhangkun
 * @time 2022/3/26 11:02 上午
 * @Description
 */
class JokeAdapter : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {


    val list = mutableListOf<DataModel>()


    fun setDataList(listData: List<DataModel>) {
        list.clear()
        list.addAll(listData)
        notifyItemRangeChanged(0, listData.size - 1)
    }


    class ViewHolder(val binding: ActivityCoroutineAdapterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataModel) {
            binding.dataModel = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ActivityCoroutineAdapterItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.activity_coroutine_adapter_item,
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


}