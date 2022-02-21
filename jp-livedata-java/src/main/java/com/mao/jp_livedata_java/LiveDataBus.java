package com.mao.jp_livedata_java;

import androidx.lifecycle.BusLiveData;

import com.mao.jp_livedata_java.core.LiveDataBusCore;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangkun
 * @time 2022/2/16 18:50
 * @Description
 */
public class LiveDataBus {

    /**
     * 同步调用
     *
     * @param key
     * @param <T>
     * @return
     */
    public static synchronized <T> BusLiveData<T> getSyn(String key) {
        return get(key);
    }

    public static <T> BusLiveData<T> get(String key) {
        return LiveDataBusCore.getInstance().getChannel(key);
    }

    /**
     * 将事件的定义由事件总线框架本身通过动态代理去实现
     *
     * @param cls
     * @param <E>
     * @return
     */
    public static <E> E of(Class<E> cls) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }

        if (0 < cls.getInterfaces().length) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }

        /*return (E) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, (proxy, method, args) -> {

            String key = cls.getCanonicalName() + "_" + method.getName();
            return get(key,
                    ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0].getClass());
        });*/

        return cls.cast(Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return get(cls.getCanonicalName() + "_" + method.getName());
            }
        }));

    }


}
