package com.mao.jetpack.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mao.jetpack.R
import com.mao.jetpack.lifecycle.MyPresenter
import com.mao.jetpack.ui.livedata.LiveDataActivity
import com.mao.jetpack.ui.livedata.LiveDataBus
import com.mao.jetpack.utils.LiveDataBusX
import com.mao.jetpack.utils.LiveDataNewBus

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(MyPresenter())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        val btnJump: Button = root.findViewById(R.id.btn_jump)
        btnJump.setOnClickListener {
            startActivity(Intent(activity, LiveDataActivity::class.java))
            LiveDataBus.getInstance().with("dashboard", String::class.java).value = "发送消消了"
            //LiveDataBusX.getInstance().with<String>("dashboard").setStickyData("LiveDataBusX 发送消消了")
            //LiveDataNewBus.with<String>("dashboard").setStickyData("LiveDataNewBus kotlin 发送消消了")
        }
        return root
    }
}