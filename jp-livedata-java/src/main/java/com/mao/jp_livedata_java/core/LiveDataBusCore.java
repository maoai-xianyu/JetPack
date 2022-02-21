package com.mao.jp_livedata_java.core;

import androidx.lifecycle.BusLiveData;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangkun
 * @time 2022/2/16 17:57
 * @Description 单利模式获取
 */
public class LiveDataBusCore {

    private final Map<String, BusLiveData<Object>> mBusMap;

    private LiveDataBusCore() {
        mBusMap = new HashMap<>();
    }

    /**
     * 单例模式实现
     */
    private static class SingletonHolder {
        private static final LiveDataBusCore DEFAULT_BUS = new LiveDataBusCore();
    }

    public static LiveDataBusCore getInstance() {
        return SingletonHolder.DEFAULT_BUS;
    }

    public <T> BusLiveData<T> getChannel(String key) {
        if (!mBusMap.containsKey(key)) {
            mBusMap.put(key, new BusLiveData<>(key));
        }
        return (BusLiveData<T>) mBusMap.get(key);
    }

    public Map<String, BusLiveData<Object>> getBusMap() {
        return mBusMap;
    }
}
