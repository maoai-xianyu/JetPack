package com.mao.base.loadsir

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.mao.base.R

/**
 *
 * @author zhangkun
 * @time 2021/9/15 10:33
 * @Description
 */
class LottieLoadingCallback : Callback() {

    override fun onCreateView(): Int = R.layout.layout_lottie_loading

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}