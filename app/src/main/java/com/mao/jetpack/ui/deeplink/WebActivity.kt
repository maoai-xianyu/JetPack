package com.mao.jetpack.ui.deeplink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mao.jetpack.databinding.ActivityWebBinding
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2021/4/20 4:36 PM
 * @Description
 */
class WebActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webview.loadUrl("file:///android_asset/" + "deeplink.html")


        binding.btn.setOnClickListener {
            try {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse("maoyan://maoyan.com/movielist")
                startActivity(intent)
            } catch (e: Exception) {

                Logger.error("跳转淘宝 奔溃 ${e.message}")
            } finally {
                Logger.error("finally 跳转猫眼")
            }
        }

        binding.btnRoom.setOnClickListener {
            try {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse("taobao://item.taobao.com/item.html?id=41700658839")
                it.context.startActivity(intent)
            } catch (e: Exception) {

                Logger.error("跳转淘宝 奔溃 ${e.message}")
            } finally {

                Logger.error("finally 跳转淘宝 ")
            }

        }
    }
}