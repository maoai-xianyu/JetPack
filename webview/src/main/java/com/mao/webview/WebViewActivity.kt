package com.mao.webview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mao.webview.databinding.ActivityWebviewBinding
import com.mao.webview.utils.IS_SHOW_ACTION_BAR
import com.mao.webview.utils.TITLE
import com.mao.webview.utils.URL

/**
 *
 * @author zhangkun
 * @time 2021/9/9 11:37
 * @Description
 */
class WebViewActivity : AppCompatActivity() {


    private lateinit var mBinding: ActivityWebviewBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mBinding.actionBar.visibility =
            if (intent.getBooleanExtra(IS_SHOW_ACTION_BAR, false)) {
                View.VISIBLE
            } else {
                View.GONE
            }
        mBinding.title.text = intent.getStringExtra(TITLE) ?: ""
        mBinding.back.setOnClickListener {
            finish()
        }

        // mBinding = DataBindingUtil.setContentView(this,R.layout.activity_webview)
        mBinding.webview.settings.javaScriptEnabled = true
        mBinding.webview.loadUrl(intent.getStringExtra(URL) ?: "")
    }
}