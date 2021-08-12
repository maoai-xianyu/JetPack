package com.mao.jetpack

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.mao.jetpack.BlockCanary.BlockCanary
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2021/3/6 6:31 PM
 * @Description
 */
class JetPackApplication : Application() {


    init {
        // 跟踪：APP启动有点卡

        // 采样，1_000采样周期 每隔一毫秒对启动过程的执行的方法进行采样
        // 8 * 1024 * 1024 文件的大小
        /*Debug.startMethodTracingSampling( File(Environment.getExternalStorageDirectory(),
            "enjoy").absolutePath, 8 * 1024 * 1024, 1_000);*/

        // 跟踪：APP启动有点卡
        /*Debug.startMethodTracing(
            File(
                Environment.getExternalStorageDirectory(),
                "enjoy"
            ).absolutePath
        )*/
    }

    override fun onCreate() {
        super.onCreate()
        Logger.debug("oncreate")
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifeCycle())

        // 检测是否有布局有卡顿
        //BlockCanary.install()
    }
}