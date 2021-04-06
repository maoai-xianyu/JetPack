package com.mao.jetpack.ui.room

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mao.jetpack.databinding.ActivityRoomSelectItemBinding

/**
 *
 * @author zhangkun
 * @time 2021/4/7 10:11 AM
 * @Description
 */
class RoomSelectAdapter(val context: Context) :
    RecyclerView.Adapter<RoomSelectAdapter.ViewHolder>() {

    private val studentList: MutableList<Student> = mutableListOf()


    fun addAllList(students: List<Student>) {
        studentList.addAll(students)
        notifyDataSetChanged()
    }


    class ViewHolder(val itemViewBinding: ActivityRoomSelectItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {


        fun setBindData(student: Student) {
            itemViewBinding.tvName.text = student.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val itemBinding = ActivityRoomSelectItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        studentList[position].let {
            holder.setBindData(it)
        }

    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}