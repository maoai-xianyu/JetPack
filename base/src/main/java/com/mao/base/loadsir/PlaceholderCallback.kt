package com.mao.base.loadsir

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.mao.base.R

/**
 *
 * @author zhangkun
 * @time 2021/9/15 10:34
 * @Description
 */
class PlaceholderCallback : Callback() {

    override fun onCreateView(): Int = R.layout.layout_placeholder

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}