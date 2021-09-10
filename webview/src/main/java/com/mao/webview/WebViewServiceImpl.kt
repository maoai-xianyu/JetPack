package com.mao.webview

import android.content.Context
import android.content.Intent
import com.mao.common.autoservice.IWebViewInterface
import com.google.auto.service.AutoService
import com.mao.webview.utils.TITLE
import com.mao.webview.utils.URL
import com.mao.webview.utils.IS_SHOW_ACTION_BAR

/**
 *
 * @author zhangkun
 * @time 2021/9/10 15:24
 * @Description
 */

@AutoService(IWebViewInterface::class)
class WebViewServiceImpl : IWebViewInterface {

    override fun startWebViewActivity(
        context: Context?,
        url: String,
        title: String,
        isShowActionBar: Boolean
    ) {

        context?.apply {
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(URL, url)
            intent.putExtra(TITLE, title)
            intent.putExtra(IS_SHOW_ACTION_BAR, isShowActionBar)
            startActivity(intent)
        }
    }

}