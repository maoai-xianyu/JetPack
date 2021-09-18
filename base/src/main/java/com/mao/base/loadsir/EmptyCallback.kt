package com.mao.base.loadsir

import com.kingja.loadsir.callback.Callback
import com.mao.base.R

/**
 *
 * @author zhangkun
 * @time 2021/9/15 10:26
 * @Description
 */
class EmptyCallback : Callback() {

    override fun onCreateView() = R.layout.layout_empty
}