package com.mao.jetpack.global;

import android.annotation.SuppressLint;
import android.app.Application;

/**
 * @author zhangkun
 * @time 2022/3/26 9:26 上午
 * @Description
 */
class AppUtils {

    private AppUtils() {

    }

    private static Application sApplication;

    public static Application getApp() {
        if (sApplication != null) return sApplication;
        return getApplicationByReflect();
    }

    private static Application getApplicationByReflect() {
        try {
            @SuppressLint("PrivateApi")
            Class<?> activityThread = Class.forName("android.app.ActivityThread");
            Object thread = activityThread.getMethod("currentActivityThread").invoke(null);
            Object app = activityThread.getMethod("getApplication").invoke(thread);
            if (app == null) {
                throw new NullPointerException("u should init first");
            }
            return (Application) app;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new NullPointerException("u should init first");
    }

    public static void init(final Application app) {
        if (sApplication == null) {
            if (app == null) {
                sApplication = getApplicationByReflect();
            } else {
                sApplication = app;
            }
        } else {
            if (app != null && app.getClass() != sApplication.getClass()) {
                sApplication = app;
            }
        }
    }
}
