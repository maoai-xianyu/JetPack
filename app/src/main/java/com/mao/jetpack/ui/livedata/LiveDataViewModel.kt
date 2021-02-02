package com.mao.jetpack.ui.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @author zhangkun
 * @time 2021/2/2 5:01 PM
 * @Description
 */
class LiveDataViewModel : ViewModel() {

    private var liveData: MutableLiveData<String>? = null

    fun getLiveData(): MutableLiveData<String> {
        if (liveData == null) {
            liveData = MutableLiveData()
        }
        return liveData!!
    }

}