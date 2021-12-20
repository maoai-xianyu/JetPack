package com.mao.common.autoservice

import android.content.Context
import androidx.fragment.app.Fragment

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


    fun getWebViewFragment(
        url: String,
        canNativeRefresh: Boolean
    ): Fragment
}