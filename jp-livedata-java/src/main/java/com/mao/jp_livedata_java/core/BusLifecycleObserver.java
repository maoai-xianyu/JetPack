package com.mao.jp_livedata_java.core;

import androidx.lifecycle.BusLiveData;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * @author zhangkun
 * @time 2022/2/16 17:36
 * @Description
 */
public class BusLifecycleObserver<T> extends BaseBusObserverWrapper<T> implements LifecycleObserver {

    private final LifecycleOwner owner;
    private final BusLiveData<T> liveData;
    private final Observer<T> observer;

    public BusLifecycleObserver(Observer<T> observer, LifecycleOwner owner, BusLiveData<T> liveData) {
        super(observer, liveData);
        this.observer = observer;
        this.owner = owner;
        this.liveData = liveData;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        liveData.removeObserver(observer);
        owner.getLifecycle().removeObserver(this);
    }
}
