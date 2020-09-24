package com.mao.jetpack.ui.livedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mao.jetpack.R
import com.mao.jetpack.utils.Logger
import kotlinx.android.synthetic.main.activity_livedata.*
import kotlin.concurrent.thread

class LiveDataActivity : AppCompatActivity() {


    lateinit var liveData: MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)

        liveData = MutableLiveData<String>()

//        liveData.value = ""
//        liveData.postValue("")

        liveData.observe(
            this,
            Observer<String> {
                Logger.debug("liveData kotlin 1 $it")
            }
        )

        liveData.observe(
            this,
            Observer<String> {
                Logger.debug("liveData kotlin 2 $it")
            }
        )


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