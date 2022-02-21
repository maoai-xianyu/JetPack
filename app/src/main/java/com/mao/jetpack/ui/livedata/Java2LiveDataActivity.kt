package com.mao.jetpack.ui.livedata

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.mao.jetpack.databinding.ActivityLivedataJava2Binding
import com.mao.jetpack.utils.Logger
import com.mao.jp_livedata_java.LiveDataBus

class Java2LiveDataActivity : AppCompatActivity() {

    lateinit var binding: ActivityLivedataJava2Binding


    val observer: Observer<PersonModel> = Observer<PersonModel>{
        Logger.debug("observeForever livedata  显示数据  ${it.name}")
        binding.tvLiveData.text = it.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLivedataJava2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 如果使用  observeForever 不关联生命周期，永久有效
       LiveDataBus.of(Events::class.java)
            .personEvent()
            .observeForever(observer)


        binding.btnJump.setOnClickListener {
            startActivity(Intent(this, JavaLiveDataActivity::class.java))

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        LiveDataBus.of(Events::class.java)
            .personEvent()
            .removeObserver(observer)
    }

}