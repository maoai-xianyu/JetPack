package com.mao.jetpack.ui.livedata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.mao.jetpack.R

class LiveDataActivity : AppCompatActivity() {


    private var liveData: MutableLiveData<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)

        liveData = MutableLiveData<String>()

    }
}