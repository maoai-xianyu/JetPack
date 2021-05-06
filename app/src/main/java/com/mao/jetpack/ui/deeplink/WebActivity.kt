package com.mao.jetpack.ui.deeplink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
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

                //openTaobao(it)  // ok
                //openFollowUs(SocialType.WX) // no ok
                //openFollowUs(SocialType.QQ) // ok
                //openFollowUs(SocialType.WEIBO) // ok
                //openFollowUs(SocialType.BS) // ok
                //openFollowUs(SocialType.XHS) // ok
                //openFollowUs(SocialType.DY) //ok
                //openFollowUs(SocialType.KS) //ok
                toTianMao("天猫")
            } catch (e: Exception) {

                Logger.error("跳转第三方 奔溃 ${e.message}")
            } finally {

                Logger.error("finally 跳转 ")
            }

        }
    }

    private fun toTianMao(url: String) {
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        val id = WebJavaActivity.getUrlParam(url, "id")
        val tmall_url = "tmall://tmallclient/?{\"action\":\"item:id=$id\"}"
        val uri = Uri.parse(tmall_url)
        intent.data = uri
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun openTaobao(v: View) {
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("taobao://item.taobao.com/item.html?id=41700658839")
        v.context.startActivity(intent)
    }

    private fun openFollowUs(type: SocialType) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(type.uri))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(intent)
        } catch (e: Exception) {
            Logger.error("跳转第三方应用 奔溃 ${e.message}")
        } finally {
            Logger.error("finally 跳转 ")
        }

    }

    enum class SocialType(val nam: String, val url: String, val uri: String) {
        WX("微信公众号", "", ""),
        QQ(
            "QQ",
            "",
            "mqqapi://card/show_pslcard?src_type=internal&version=1&uin=1519269558&card_type=person&source=qrcode"
        ),
        WEIBO("微博", "https://weibo.com/u/id", "sinaweibo://userinfo?uid=id"),
        BS("B站", "https://space.bilibili.com/id", "bilibili://space/id"),
        XHS("小红书", "https://www.xiaohongshu.com/user/profile/id", "xhsdiscover://user/id"),
        DY(
            "抖音",
            "http://v.douyin.com/id",
            "snssdk1128://user/profile/id?refer=web&gd_label=click_wap_profile_follow&type=need_follow&needlaunchlog=1"
        ),
        KS("快手", "http://m.gifshow.com/s/id", "kwai://profile/id")
    }
}