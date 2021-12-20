package com.mao.webview.webviewclient

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mao.webview.WebViewCallBack

/**
 *
 * @author zhangkun
 * @time 2021/9/15 17:39
 * @Description
 */
class JPWebViewClient(val mWebViewCallBack: WebViewCallBack?) : WebViewClient() {

    val TAG: String = "JPWebViewClient"

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        mWebViewCallBack?.pageFinished(url) ?: Log.e(
            TAG,
            "mWebViewCallBack is null : onPageFinished"
        )
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        mWebViewCallBack?.pageStarted(url) ?: Log.e(TAG, "mWebViewCallBack is null : onPageStarted")
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        mWebViewCallBack?.pageOnError() ?: Log.e(TAG, "mWebViewCallBack is null : onReceivedError")
    }
}