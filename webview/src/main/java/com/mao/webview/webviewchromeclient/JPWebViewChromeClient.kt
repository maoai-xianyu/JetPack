package com.mao.webview.webviewchromeclient

import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.mao.webview.WebViewCallBack

/**
 *
 * @author zhangkun
 * @time 2021/9/17 17:52
 * @Description
 */
class JPWebViewChromeClient(val mWebViewCallBack: WebViewCallBack?) : WebChromeClient() {

    val TAG: String = "JPWebViewChromeClient"

    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)

        mWebViewCallBack?.updateTitle(title) ?: Log.d(TAG, "onReceivedTitle update title")
    }


}