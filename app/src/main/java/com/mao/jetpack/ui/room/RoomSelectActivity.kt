package com.mao.jetpack.ui.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.mao.jetpack.databinding.ActivityRoomBinding
import com.mao.jetpack.databinding.ActivityRoomSelectBinding
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2021/3/30 10:17 AM
 * @Description
 */
class RoomSelectActivity : AppCompatActivity() {

    lateinit var rootBinding: ActivityRoomSelectBinding

    private val adapter: RoomSelectAdapter by lazy {
        RoomSelectAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootBinding = ActivityRoomSelectBinding.inflate(layoutInflater)
        setContentView(rootBinding.root)

        rootBinding.rv.adapter = adapter

        val viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        //val viewModelJava = ViewModelProvider(this).get(RoomViewModelJava::class.java)

        viewModel.getLiveDataAllStudent().observe(this) {

            Logger.error("it $it")
            adapter.addAllList(it)
        }

    }
}