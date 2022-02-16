package com.mao.jetpack.ui.livedata

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.mao.jetpack.databinding.ActivityLivedataNewBinding
import com.mao.jetpack.databinding.ActivityLivedataNewDBinding
import com.mao.jetpack.utils.Logger
import com.mao.jp_livedata.LiveDataBus

class LiveDataNewDActivity : AppCompatActivity() {

    lateinit var binding: ActivityLivedataNewDBinding


    val observer: Observer<PersonModel> = Observer<PersonModel>{
        Logger.debug("LiveDataNewDActivity livedata  显示数据  ${it.name}")
        binding.tvLiveData.text = it.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLivedataNewDBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 如果使用  observeForever 不关联生命周期，永久有效
        LiveDataBus.of(Events::class.java)
            .personEvent()
            .observeForever(observer)


        binding.btnJump.setOnClickListener {
            startActivity(Intent(this, LiveDataNewActivity::class.java))

        }
    }


}