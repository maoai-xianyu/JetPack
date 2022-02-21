package com.mao.jetpack.ui.livedata

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.mao.jetpack.databinding.ActivityLivedataJavaBinding
import com.mao.jetpack.utils.Logger
import com.mao.jp_livedata_java.LiveDataBus

class JavaLiveDataActivity : AppCompatActivity() {

    lateinit var binding: ActivityLivedataJavaBinding


    val observer: Observer<PersonModel> = Observer<PersonModel>{
        Logger.debug("observeForever livedata  显示数据  ${it.name}")
        binding.tvLiveData.text = it.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLivedataJavaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 粘性事件，新打开的页面也是订阅之后，也是可以收到数据的
        // 非粘性事件，新打开的页面，没有对应的数据
        LiveDataBus.of(Events::class.java)
            .personEvent()
            .observe(this) {
                Logger.debug(" observeSticky livedata  显示数据  ${it.name}")
                binding.tvLiveData.text = it.name
            }
        // 如果使用  observeForever 不关联生命周期，永久有效
        /*LiveDataBus.of(Events::class.java)
            .personEvent()
            .observeForever(observer)*/

        binding.btnJump.setOnClickListener {
            startActivity(Intent(this, JavaLiveDataActivity::class.java))
        }


        binding.btnSend.setOnClickListener {

            LiveDataBus.of(Events::class.java).personEvent().postValue(PersonModel("猫眼娱乐"))
        }

        binding.btnJumpD.setOnClickListener {
            startActivity(Intent(this, LiveDataNewDActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        LiveDataBus.of(Events::class.java)
            .personEvent()
            .removeObserver(observer)
    }

}