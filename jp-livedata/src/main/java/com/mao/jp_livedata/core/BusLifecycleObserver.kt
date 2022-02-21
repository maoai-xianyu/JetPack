package com.mao.jp_livedata.core

import androidx.lifecycle.*
import androidx.lifecycle.BusLiveData
import com.mao.jp_livedata.Logger

internal class BusLifecycleObserver<T>(private val observer: Observer<in T>, private val owner: LifecycleOwner, private val liveData: BusLiveData<T>)
    : BaseBusObserverWrapper<T>(observer,liveData), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        Logger.e("------","自动管理")
        liveData.removeObserver(observer)
        owner.lifecycle.removeObserver(this)
    }
}