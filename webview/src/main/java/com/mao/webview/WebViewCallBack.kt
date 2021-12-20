package com.mao.webview

/**
 *
 * @author zhangkun
 * @time 2021/9/15 18:02
 * @Description
 */
interface WebViewCallBack {

    fun pageStarted(url: String?)

    fun pageFinished(url: String?)

    fun pageOnError()

    fun updateTitle(title: String?)
}