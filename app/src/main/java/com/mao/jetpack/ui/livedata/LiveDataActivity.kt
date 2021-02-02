package com.mao.jetpack.ui.livedata

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mao.jetpack.R
import com.mao.jetpack.utils.Logger
import kotlinx.android.synthetic.main.activity_livedata.*
import kotlin.concurrent.thread

class LiveDataActivity : AppCompatActivity() {


    private lateinit var viewModel: LiveDataViewModel

    private lateinit var liveData: MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)
        viewModel = ViewModelProvider(this).get(LiveDataViewModel::class.java)

        liveData = viewModel.getLiveData()

        // 需要一个观察者观察数据
        val observer = Observer<String> {
            tvLiveData.text = "liveData kotlin 1 $it"
        }

        // livedata 订阅 observer ，livedata 就是被观察者
        liveData.observe(this, observer)

        tvButton.setOnClickListener {
            /*thread {*/
            // 主线程,不能在子线程中使用
            // java.lang.IllegalStateException: Cannot invoke setValue on a background thread
            // liveData.value = "11111"
            /*}*/

            thread {
                // 任意线程
                liveData.postValue("22222222")
            }

        }

    }
}