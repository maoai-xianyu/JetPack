package com.mao.jetpack

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2021/3/6 6:31 PM
 * @Description
 */
class JetPackApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Logger.debug("oncreate")
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifeCycle())
    }
}