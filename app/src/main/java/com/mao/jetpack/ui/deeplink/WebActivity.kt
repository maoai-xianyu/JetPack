package com.mao.jetpack.ui.deeplink

import android.annotation.SuppressLint
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

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webview.loadUrl("file:///android_asset/" + "deeplink.html")


        binding.btn.setOnClickListener {
            var success = "成功"
            try {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                //intent.data = Uri.parse("maoyan://maoyan.com/movielist")
                //intent.data = Uri.parse("maoyan://maoyan.com/movie?id=1250952")
                //intent.data = Uri.parse("maoyan://maoyan.com/videolist?id=1334342&videoid=454570")
                // 用以判断当前是否有对应的scheme的应用，有就打开
                //intent.data = Uri.parse("maoyan://maoyan.com/movie/hotTopicLists?movieid=1288304&topicid=135556655")
                intent.data = Uri.parse("maoyan://maoyan.com/mine/orderlist/seatorder?orderId=23032604633")
                // 直接打开网页 下面的会作为一个网页打开
                //intent.data = Uri.parse("https://m.maoyan.com/asgard/movie/1334342/preview?videoId=456111")
                //intent.data = Uri.parse("https://m.maoyan.com")
                // 打开网页
                //intent.data = Uri.parse("https://mao.com")
                // 可以打开当前目录
                //intent.data = Uri.parse("jetpack://deeplink")
                //intent.data = Uri.parse("meituanmovie://www.meituan.com/movie/hotTopicLists?movieid=1288304&topicid=135556655")
                //intent.data = Uri.parse("maoyan://maoyan.com/movie/movie?id=1288304")
                //intent.data = Uri.parse("maoyan://maoyan.com/movie?id=1250952")
                startActivity(intent)
            } catch (e: Exception) {
                success = "失败"
                Logger.error("Exception 跳转猫眼 奔溃 ${e.message}")
            } finally {
                Logger.error("finally 跳转猫眼 $success")
            }
        }

        binding.btnMeituan.setOnClickListener {
            var success = "成功"
            try {
                //val intent = Intent()
                //intent.action = Intent.ACTION_VIEW
                //intent.data = Uri.parse("imeituan://www.meituan.com/movie_orderdetail?orderId=23032604633&userId=50108998")
                //startActivity(intent)

                val intent =  Intent()
                intent.action = "android.intent.action.VIEW";
                intent.data = Uri.parse("imeituan://www.meituan.com/movie_orderdetail?orderId=23032604633&userId=50108998")
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                baseContext.startActivity(intent)

                //intent.flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT  // 可以一直打开
                //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP  // 可以一直打开
                //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK // 可以一直打开
                //intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP  // 可以一直打开

            } catch (e: Exception) {
                success = "失败"
                Logger.error("Exception 跳转猫眼 奔溃 ${e.message}")
            } finally {
                Logger.error("finally 跳转猫眼 $success")
            }
        }

        binding.btnRoom.setOnClickListener {

            var success = "第三方成功"
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
                success = "第三方失败"
                Logger.error("跳转第三方 奔溃 ${e.message}")
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse("maoyan://maoyan.com/movielist")
                startActivity(intent)
            } finally {

                Logger.error("finally 跳转 ")
            }

        }

        binding.btnRoomJ.setOnClickListener {
            try {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW

                intent.data =
                    Uri.parse("maoyan://maoyan.com/movie_trailer?id=1334342&videoid=454570")
                val resolveActivity = intent.resolveActivity(packageManager)
                if (resolveActivity != null) {
                    startActivity(intent)
                    Logger.error("跳转第三方 成功跳转, 拉不去起来，是因为没有对应的链接")
                } else {
                    Logger.error("跳转第三方 失败跳转")
                }
            } catch (e: Exception) {
                Logger.error("Exception 没有类 奔溃 ${e.message}")
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