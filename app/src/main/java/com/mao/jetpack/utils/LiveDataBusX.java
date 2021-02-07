package com.mao.jetpack.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangkun
 * @time 2021/2/3 3:05 PM
 * @Description liveData 总线
 */
public class LiveDataBusX {

    private static ConcurrentHashMap<String, StickyLiveData<Object>> mHashMap;

    private LiveDataBusX() {
        mHashMap = new ConcurrentHashMap<>();
    }

    public static LiveDataBusX getInstance() {
        return LiveDataBusXInner.instance;
    }

    private static class LiveDataBusXInner {
        private static final LiveDataBusX instance = new LiveDataBusX();
    }

    public synchronized <T> StickyLiveData<T> with(String eventName) {
        if (!mHashMap.containsKey(eventName)) {
            mHashMap.put(eventName, new StickyLiveData<>(eventName));
        }
        return (StickyLiveData<T>) mHashMap.get(eventName);
    }

    private static class StickyObserver<T> implements Observer<T> {
        private StickyLiveData<T> mLiveData;
        private Observer<T> mObserver;
        private boolean mSticky;

        //标记该observer已经接收几次数据了，用以过滤老数据重复接收
        private int mLastVersion = 0;

        public StickyObserver(StickyLiveData<T> liveData, Observer<T> observer, boolean sticky) {
            this.mLiveData = liveData;
            this.mObserver = observer;
            this.mSticky = sticky;
            //比如先使用StickyLiveData发送了一条数据。StickyLiveData#version=1
            //那当我们创建WrapperObserver注册进去的时候
            //需要把它的version和 StickyLiveData的version保持一致
            //用以过滤老数据，否则 岂不是会收到老的数据？
            mLastVersion = mLiveData.mVersion;
        }

        @Override
        public void onChanged(T t) {

            if (mLastVersion >= mLiveData.mVersion) {
                //但如果当前observer它是关心 黏性事件的，则给他。
                if (mSticky && mLiveData.mStickyData != null) {
                    mObserver.onChanged(mLiveData.mStickyData);
                }
                return;
            }
            mLastVersion = mLiveData.mVersion;
            mObserver.onChanged(t);
        }
    }

    public static class StickyLiveData<T> extends LiveData<T> {
        private String mEventName;
        private T mStickyData;
        private int mVersion = 0;

        public StickyLiveData(String eventName) {
            mEventName = eventName;
        }

        @Override
        public void setValue(T value) {
            //每次发送消息，版本号就需要+1，因为我们需要通过这个version管控，要不要分发黏性事件。
            mVersion++;
            super.setValue(value);
        }

        @Override
        public void postValue(T value) {
            super.postValue(value);
        }

        public void setStickyData(T stickyData) {
            //同步的方式发送黏性消息
            this.mStickyData = stickyData;
            setValue(stickyData);
        }

        public void postStickyData(T stickyData) {
            //异步的形式发送消息
            this.mStickyData = stickyData;
            postValue(stickyData);
        }

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            observerSticky(owner, observer, false);
        }

        public void observerSticky(LifecycleOwner owner, Observer<? super T> observer, boolean sticky) {
            owner.getLifecycle().addObserver(new LifecycleEventObserver() {
                @Override
                public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        //自动反注册
                        mHashMap.remove(mEventName);
                    }
                }
            });
            super.observe(owner, new StickyObserver<T>(this, (Observer<T>) observer, sticky));
        }
    }
}
