package com.mao.jetpack.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mao.jetpack.R
import com.mao.jetpack.adapter.ProgressShowAdapter
import com.mao.jetpack.model.ProgressBarModel
import com.mao.jetpack.ui.room.RoomActivity
import com.mao.jetpack.ui.room.RoomSelectActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn.setOnClickListener {
            startActivity(Intent(activity, RoomActivity::class.java))
        }

        btnRoom.setOnClickListener {
            startActivity(Intent(activity, RoomSelectActivity::class.java))
        }

        val p1 = ProgressBarModel(0)
        val p2 = ProgressBarModel(0)
        val p3 = ProgressBarModel(0)
        val p4 = ProgressBarModel(0)

        val list = arrayListOf<ProgressBarModel>()

        list.add(p1)
        list.add(p2)
        list.add(p3)
        list.add(p4)

        val adapter = ProgressShowAdapter(list)
        rv.adapter = adapter

        rv.postDelayed({

            adapter.playAn = true
            list[0].progress = 20
            list[1].progress = 30
            list[2].progress = 40
            list[3].progress = 50

            adapter.notifyDataSetChanged()

            rv.postDelayed({
                adapter.playAn = false
            },500)

        }, 5000)





    }
}