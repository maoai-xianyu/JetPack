package com.mao.webview

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.mao.base.loadsir.ErrorCallback
import com.mao.base.loadsir.LoadingCallback
import com.mao.webview.databinding.FragmentWebviewBinding
import com.mao.webview.utils.URL
import com.mao.webview.utils.CAN_NATIVE_REFRESH
import com.mao.webview.webviewchromeclient.JPWebViewChromeClient
import com.mao.webview.webviewclient.JPWebViewClient
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener

/**
 *
 * @author zhangkun
 * @time 2021/9/14 10:51
 * @Description
 */
class WebViewFragment : Fragment(), WebViewCallBack, OnRefreshListener {

    private val TAG = "WebViewFragment"
    private lateinit var mUrl: String
    private lateinit var mBinding: FragmentWebviewBinding
    private var canNativeRefresh: Boolean = false
    private var mIsError: Boolean = false

    private var loadService: LoadService<View>? = null

    companion object {
        fun getInstance(url: String, canNativeRefresh: Boolean): WebViewFragment {
            val bundle = Bundle()
            bundle.putString(URL, url)
            bundle.putBoolean(CAN_NATIVE_REFRESH, canNativeRefresh)
            return WebViewFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = arguments ?: return
        mUrl = arguments.getString(URL, "")
        canNativeRefresh = arguments.getBoolean(CAN_NATIVE_REFRESH, false)
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 第一步：获取布局View
        mBinding = FragmentWebviewBinding.inflate(layoutInflater, container, false)
        mBinding.smartRefreshLayout.setEnableRefresh(canNativeRefresh)
        mBinding.smartRefreshLayout.setEnableLoadMore(false)
        mBinding.smartRefreshLayout.setOnRefreshListener(this)
        // mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_webview, container, false)
        mBinding.webview.settings.javaScriptEnabled = true
        mBinding.webview.loadUrl(mUrl)
        // 第二步：注册布局View
        loadService = LoadSir.getDefault().register(mBinding.root) {
            loadService?.showCallback(LoadingCallback::class.java)
            mBinding.webview.reload()

        } as LoadService<View>?
        // 设置 WebViewClient
        mBinding.webview.webViewClient = JPWebViewClient(this)
        mBinding.webview.webChromeClient = JPWebViewChromeClient(this)
        // 第三步：返回LoadSir生成的LoadLayout
        return loadService?.loadLayout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun pageStarted(url: String?) {
        Log.d(TAG, "WebViewFragment pageStarted")
        loadService?.showCallback(LoadingCallback::class.java)
    }

    override fun pageFinished(url: String?) {
        mBinding.smartRefreshLayout.setEnableRefresh(
            if (mIsError) {
                true
            } else {
                canNativeRefresh
            }
        )
        Log.d(TAG, "WebViewFragment pageFinished")
        mBinding.smartRefreshLayout.finishRefresh()
        if (!mIsError) {
            loadService?.showSuccess()
        }
        mIsError = false
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mBinding.webview.reload()
    }

    override fun pageOnError() {
        Log.e(TAG, "WebViewFragment pageOnError")
        mIsError = true
        mBinding.smartRefreshLayout.finishRefresh()
        loadService?.showCallback(ErrorCallback::class.java)
    }

    override fun updateTitle(title: String?) {
        if (activity is WebViewActivity) {
            (activity as WebViewActivity).updateTiTle(title)
        }
    }
}