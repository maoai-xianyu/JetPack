package com.mao.jetpack.ui.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mao.jetpack.databinding.ActivityRoomBinding
import com.mao.jetpack.databinding.ActivityRoomSelectBinding

/**
 *
 * @author zhangkun
 * @time 2021/3/30 10:17 AM
 * @Description
 */
class RoomSelectActivity : AppCompatActivity() {

    lateinit var rootBinding: ActivityRoomSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rootBinding = ActivityRoomSelectBinding.inflate(layoutInflater)

        val viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        //val viewModelJava = ViewModelProvider(this).get(RoomViewModelJava::class.java)

    }
}