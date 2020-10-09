package com.mao.lib_common.utils;

import android.app.Application;

import java.lang.reflect.Method;

/**
 * @author zhangkun
 * @time 2020/9/27 8:48 PM
 * @Description 获取上下文
 */
public class ApplicationGlobals {

    //不依靠任务地方传递过来，要获取到上下文 怎么获取? 反射，获取到ActivityThread 中的 Application
    private static Application sApplication;


    protected static final Class<?> activityThreadClass;
    protected static final Method currentApplicationMethod;

    static {
        activityThreadClass = getActivityThreadClass();
        currentApplicationMethod = getApplicationMethod(activityThreadClass);
    }

    public static Application getApplication() {
        // 初始化application
        if (sApplication == null) {
            try {
                sApplication = (Application) currentApplicationMethod.invoke(null,  null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sApplication;
    }


    private static Class<?> getActivityThreadClass() {
        try {
            return Class.forName("android.app.ActivityThread");
        } catch (Throwable t) {
            return null;
        }
    }

    private static Method getApplicationMethod(Class<?> activityThreadClass) {
        if (activityThreadClass == null) {
            return null;
        }
        try {
            Method performStop = activityThreadClass.getDeclaredMethod("currentApplication");
            return performStop;
        } catch (Throwable t) {
            return null;
        }
    }
}
