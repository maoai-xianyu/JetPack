package com.mao.jetpack.BlockCanary;

import android.os.Looper;


/**
 * Android主线程更新UI。如果界面1秒钟刷新少于60次，即FPS小于60，用户就会产生卡顿感觉。简单来说，
 * Android使用消息机制进行UI更新，UI线程有个Looper，在其loop方法中会不断取出message，调用其绑定的
 * Handler在UI线程执行。如果在handler的dispatchMesaage方法里有耗时操作，就会发生卡顿。
 */
public class BlockCanary {
    public static void install() {
        LogMonitor logMonitor = new LogMonitor();
        Looper.getMainLooper().setMessageLogging(logMonitor);
    }
}
