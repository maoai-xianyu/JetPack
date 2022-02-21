package com.mao.jp_livedata.core


import androidx.lifecycle.BusLiveData
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.mao.jp_livedata.Logger

internal open class BaseBusObserverWrapper<T>(private val mObserver: Observer<in T>, private val mBusLiveData: BusLiveData<T>) : Observer<T> {

    private val mLastVersion = mBusLiveData.version

    private val TAG = "BaseBusObserverWrapper"

    override fun onChanged(t: T?) {
        Logger.d(TAG,"msg receiver = " + t.toString())
        if (mLastVersion >= mBusLiveData.version){
            // LiveData 的版本号没有更新过，说明并没有新数据，只是因为
            // 当前Observer的版本号比 LiveData 低导致的调用 onChange()
            return
        }
        try {
            mObserver.onChanged(t)
        }catch (e:Exception){
            Logger.e(TAG,"error on Observer onChanged() = " + e.message)
        }
    }
}

