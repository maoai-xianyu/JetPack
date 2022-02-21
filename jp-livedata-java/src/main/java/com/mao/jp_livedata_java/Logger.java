package com.mao.jp_livedata_java;

import android.util.Log;


/**
 * @author zhangkun
 * @time 2022/2/16 17:23
 * @Description  日志工具类
 */
public class Logger {
    private final static boolean DEBUG = true;

    private final static String TAG = "LiveDataBus";

    public static void d(String tag, String msg){
        if (DEBUG){
            Log.d(TAG, tag + "——" +msg);
        }
    }

    public static void v(String tag, String msg){
        if (DEBUG){
            Log.v(TAG, tag + "——" +msg);
        }
    }

    public static void i(String tag, String msg){
        if (DEBUG){
            Log.i(TAG, tag + "——" +msg);
        }
    }

    public static void w(String tag, String msg){
        if (DEBUG){
            Log.w(TAG, tag + "——" +msg);
        }
    }

    public static void e(String tag, String msg){
        if (DEBUG){
            Log.e(TAG, tag + "——" +msg);
        }
    }

}
