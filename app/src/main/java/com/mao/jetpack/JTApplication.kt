package com.mao.jetpack

import androidx.lifecycle.ProcessLifecycleOwner
import com.mao.base.BaseApplication
import com.mao.jetpack.utils.Logger

import com.kingja.loadsir.core.LoadSir
import com.mao.base.loadsir.*


/**
 *
 * @author zhangkun
 * @time 2021/9/15 10:10
 * @Description
 */
class JTApplication : BaseApplication() {


    override fun onCreate() {
        super.onCreate()
        Logger.debug("oncreate")

        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifeCycle())

        LoadSir.beginBuilder()
            .addCallback(ErrorCallback()) //添加各种状态页
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(TimeoutCallback())
            .addCallback(CustomCallback())
            .setDefaultCallback(LoadingCallback::class.java) //设置默认状态页
            .commit()
    }

}