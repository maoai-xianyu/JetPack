package com.mao.jetpack.ui.deeplink

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mao.jetpack.databinding.ActivityDeeplinkBinding

/**
 *
 * @author zhangkun
 * @time 2021/4/20 4:33 PM
 * @Description
 */
class DeepLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDeeplinkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.let {

            val uri = it.data

            uri?.let { uri ->

                val scheme = uri.scheme

                val host = uri.host

                if ("jetpack" == scheme) {
                    binding.tvShow.text =
                        "host:" + host + "\nscheme:" + scheme + "\n值：" + uri.getPathSegments()
                }
            }

        }

    }

}