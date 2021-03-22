package com.mao.jetpack.global

import android.app.Application

/**
 *
 * @author zhangkun
 * @time 2021/3/11 10:56 AM
 * @Description
 */
object AppGlobalsKt {

    private var sApplication: Application? = null

    fun get(): Application? {

        if (sApplication == null) {
            try {
                val method = Class.forName("android.app.ActivityThread")
                    .getMethod("currentApplication")
                sApplication = method.invoke(null) as Application
                // 下面这种写法是错的
                //sApplication = method.invoke(null, null as Array<Any?>?) as Application
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }

        return sApplication
    }
}