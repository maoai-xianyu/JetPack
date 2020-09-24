package com.mao.jetpack.ui.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangkun
 * @time 2020/9/24 8:58 PM
 * @Description 放在公共库
 */
public class LiveDataBus {

    private static LiveDataBus liveDataBus = new LiveDataBus();

    // 持有了当前应用的所有的liveData对象
    private Map<String, MutableLiveData<Object>> map;

    private LiveDataBus() {
        map = new HashMap<>();
    }

    public static LiveDataBus getInstance() {
        return liveDataBus;
    }

    public <T> MutableLiveData<T> with(String key, Class<T> type) {
        if (!map.containsKey(key)) {
            map.put(key, new MutableLiveData<Object>());
        }
        MutableLiveData<Object> objectMutableLiveData = map.get(key);
        return (MutableLiveData<T>) objectMutableLiveData;
    }


    // 自定义 MutableLiveData
    class DefineBus<T> extends MutableLiveData<T> {


        private boolean isSticky;

        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            super.observe(owner, observer);
            // 通过反射 吧mVersion 的值赋值给 observer 的 mLastVersion
            if (isSticky) {
                // 去反射
            }
        }


        /**
         * @param owner
         * @param observer
         * @param isSticky 是否需要粘性事件
         */
        public void observe(@NonNull LifecycleOwner owner,
                            @NonNull Observer<? super T> observer,
                            boolean isSticky) {
            this.isSticky = isSticky;
            observe(owner, observer);
        }
    }
}
