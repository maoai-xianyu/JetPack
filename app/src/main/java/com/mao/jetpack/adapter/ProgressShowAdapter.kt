package com.mao.jetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mao.jetpack.R
import com.mao.jetpack.databinding.ItemProgressBinding
import com.mao.jetpack.model.ProgressBarModel
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2021/5/22 12:01
 * @Description
 */
class ProgressShowAdapter(val datas: List<ProgressBarModel>) :
    RecyclerView.Adapter<ProgressShowAdapter.ViewHolder>() {


    var playAn= false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            ItemProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Logger.error("datas ${datas.size}")
        bindData(holder,datas[position])
    }

    fun bindData(holder:ViewHolder,progressBarModel: ProgressBarModel) {

        if (playAn){
            holder.binding.rcpb.enableAnimation();
        }else{
            holder.binding.rcpb.disableAnimation()
        }

        holder.binding.rcpb.setProgress(progressBarModel.progress)
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class ViewHolder(val binding: ItemProgressBinding) : RecyclerView.ViewHolder(binding.root) {


    }
}