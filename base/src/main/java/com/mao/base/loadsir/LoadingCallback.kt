package com.mao.base.loadsir

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.mao.base.R

/**
 *
 * @author zhangkun
 * @time 2021/9/15 10:29
 * @Description
 */
class LoadingCallback : Callback() {


    override fun onCreateView(): Int = R.layout.layout_loading

    override fun getSuccessVisible(): Boolean {
        return super.getSuccessVisible()
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}