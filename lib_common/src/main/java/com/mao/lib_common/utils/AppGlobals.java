package com.mao.lib_common.utils;

import android.app.Application;

import java.lang.reflect.Method;

/**
 * @author zhangkun
 * @time 2020/9/27 8:48 PM
 * @Description 获取上下文
 */
public class AppGlobals {

    //不依靠任务地方传递过来，要获取到上下文 怎么获取? 反射，获取到ActivityThread 中的 Application
    private static Application sApplication;

    public static Application getApplication() {
        if (sApplication == null) {
            // 初始化application
            try {
                Class<?> aClass = Class.forName("android.app.ActivityThread");
                Method currentApplication = aClass.getDeclaredMethod("currentApplication");
                sApplication = (Application) currentApplication.invoke(null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sApplication;
    }
}
