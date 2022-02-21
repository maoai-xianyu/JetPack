package com.mao.jp_livedata_java.core;

import androidx.lifecycle.BusLiveData;
import androidx.lifecycle.Observer;

import com.mao.jp_livedata_java.Logger;

/**
 * @author zhangkun
 * @time 2022/2/16 17:23
 * @Description
 */
abstract public class BaseBusObserverWrapper<T> implements Observer<T> {

    private final BusLiveData<T> mBusLiveData;

    private final Observer<T> mObserver;

    private final int mLastVersion;

    private final String TAG = "BaseBusObserverWrapper";

    public BaseBusObserverWrapper(Observer<T> mObserver, BusLiveData<T> mBusLiveData) {
        this.mObserver = mObserver;
        this.mBusLiveData = mBusLiveData;
        this.mLastVersion = mBusLiveData.getVersion();
    }


    @Override
    public void onChanged(T t) {
        if (mLastVersion >= mBusLiveData.getVersion()) {
            // LiveData 的版本号没有更新过，说明并没有新数据，只是因为
            // 当前Observer的版本号比 LiveData 低导致的调用 onChange()
            return;
        }
        try {
            mObserver.onChanged(t);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(TAG, "error on Observer onChanged() = " + e.getMessage());
        }
    }
}
