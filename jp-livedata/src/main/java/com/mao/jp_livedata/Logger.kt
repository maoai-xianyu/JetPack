package com.mao.jp_livedata

import android.util.Log

/**
 * 日志工具类
 * @author linyaotian
 */
object Logger {
    private const val DEBUG = true
    private const val TAG = "LiveDataBus"
    fun d(tag: String, msg: String) {
        if (DEBUG) {
            Log.d(TAG, "$tag——$msg")
        }
    }

    fun v(tag: String, msg: String) {
        if (DEBUG) {
            Log.v(TAG, "$tag——$msg")
        }
    }

    fun i(tag: String, msg: String) {
        if (DEBUG) {
            Log.i(TAG, "$tag——$msg")
        }
    }

    fun w(tag: String, msg: String) {
        if (DEBUG) {
            Log.w(TAG, "$tag——$msg")
        }
    }

    fun e(tag: String, msg: String) {
        if (DEBUG) {
            Log.e(TAG, "$tag——$msg")
        }
    }
}