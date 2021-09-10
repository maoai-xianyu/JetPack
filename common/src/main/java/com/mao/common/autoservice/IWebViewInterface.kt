package com.mao.common.autoservice

import android.content.Context

/**
 *
 * @author zhangkun
 * @time 2021/9/9 21:39
 * @Description
 */
interface IWebViewInterface {
    fun startWebViewActivity(
        context: Context?,
        url: String,
        title: String,
        isShowActionBar: Boolean
    )
}