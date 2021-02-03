package com.mao.jetpack.ui.livedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mao.jetpack.R
import com.mao.jetpack.utils.LiveDataBusX
import com.mao.jetpack.utils.LiveDataNewBus
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

        /*LiveDataBus.getInstance().with("dashboard", String::class.java)
            .observe(
                this,
                Observer<String> {
                    tvLiveData.text = it
                }, true
            )*/


        LiveDataBusX.getInstance().with<String>("dashboard")
            .observerSticky(this, Observer<String> {
                tvLiveData.text = it
            }, true)

        // 不接收粘性事件
        /*LiveDataNewBus.with<String>("dashboard").observe(this, Observer {
            tvLiveData.text = it
        })*/

        // 接收粘性事件
        /*LiveDataNewBus.with<String>("dashboard").observerSticky(this, true, Observer {
            tvLiveData.text = it
        })*/

        liveData = viewModel.getLiveData()

        //如果先发射数据，后订阅观察者，会受到之前发送的消息
        //liveData.value = "我发射数据了"

        // 需要一个观察者观察数据
        val observer = Observer<String> {
            Logger.debug("接受到消息")
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