package com.mao.base

import android.app.Application

/**
 *
 * @author zhangkun
 * @time 2021/9/15 10:04
 * @Description
 */
open class BaseApplication : Application() {

    companion object {
        @JvmStatic
        @get:JvmName("currentApplication")
        lateinit var currentApplication: Application
            private set
    }

    override fun onCreate() {
        super.onCreate()
        currentApplication = this
    }
}