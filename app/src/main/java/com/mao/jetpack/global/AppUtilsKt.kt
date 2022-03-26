package com.mao.jetpack.global

import android.annotation.SuppressLint
import android.app.Application
import java.lang.NullPointerException

/**
 *
 * @author zhangkun
 * @time 2022/3/26 9:32 上午
 * @Description
 */
object AppUtilsKt {

    val sApplication: Application
        @SuppressLint("PrivateApi")
        get() = try {
            val method = Class.forName("android.app.ActivityThread")
                .getMethod("currentApplication")
            method.invoke(null) as Application
        } catch (ex: Exception) {
            ex.printStackTrace()
            throw NullPointerException()
        }
}