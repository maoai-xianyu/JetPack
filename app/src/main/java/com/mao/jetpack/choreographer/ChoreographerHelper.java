package com.mao.jetpack.choreographer;

import android.os.Build;
import android.view.Choreographer;


/**
 * Android系统每隔16ms发出VSYNC信号，来通知界面进行重绘、渲染，每一次同步的周期约为16.6ms，代表一帧
 * 的刷新频率。通过Choreographer类设置它的FrameCallback函数，当每一帧被渲染时会触发回调
 * FrameCallback.doFrame (long frameTimeNanos) 函数。frameTimeNanos是底层VSYNC信号到达的时间戳
 */
public class ChoreographerHelper {

    static long lastFrameTimeNanos = 0;

    public static void start() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {

                @Override
                public void doFrame(long frameTimeNanos) {
                    //上次回调时间
                    if (lastFrameTimeNanos == 0) {
                        lastFrameTimeNanos = frameTimeNanos;
                        Choreographer.getInstance().postFrameCallback(this);
                        return;
                    }
                    long diff = (frameTimeNanos - lastFrameTimeNanos) / 1_000_000;
                    if (diff > 16.6f) {
                        //掉帧数
                        int droppedCount = (int) (diff / 16.6);
                    }
                    lastFrameTimeNanos = frameTimeNanos;
                    Choreographer.getInstance().postFrameCallback(this);
                }
            });
        }
    }
}
