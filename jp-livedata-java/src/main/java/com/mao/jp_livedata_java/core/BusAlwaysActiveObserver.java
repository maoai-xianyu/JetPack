package com.mao.jp_livedata_java.core;

import androidx.lifecycle.BusLiveData;
import androidx.lifecycle.Observer;

/**
 * @author zhangkun
 * @time 2022/2/16 17:55
 * @Description
 */
public class BusAlwaysActiveObserver<T> extends BaseBusObserverWrapper<T> {

    public BusAlwaysActiveObserver(Observer<T> mObserver, BusLiveData<T> mBusLiveData) {
        super(mObserver, mBusLiveData);
    }
}
