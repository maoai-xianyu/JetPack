package com.mao.base.loadsir

import android.content.Context
import android.view.View
import android.widget.Toast
import com.kingja.loadsir.callback.Callback
import com.mao.base.R

/**
 *
 * @author zhangkun
 * @time 2021/9/15 10:35
 * @Description
 */
class TimeoutCallback : Callback() {

    override fun onCreateView(): Int = R.layout.layout_timeout

    override fun onReloadEvent(context: Context, view: View?): Boolean {
        Toast.makeText(
            context.applicationContext,
            "Connecting to the network again!",
            Toast.LENGTH_SHORT
        ).show()
        return false
    }
}