package com.mao.jetpack.ui.banner


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide
import com.mao.jetpack.R
import com.mao.jetpack.bannerloop.BannerAdapter
import com.mao.jetpack.bannerloop.BannerView
import com.mao.jetpack.bannerloop.BannerViewPager
import com.mao.jetpack.bannerloop.Banners
import com.mao.jetpack.databinding.ActivtiyBannerBinding

/**
 *
 * @author zhangkun
 * @time 2021/11/10 14:54
 * @Description
 */
class BannerActivity : AppCompatActivity(), BannerViewPager.BannerItemClickListener {

    lateinit var binding: ActivtiyBannerBinding

    val banners = mutableListOf<Banners>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivtiyBannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        banners.add(
            Banners(
                "http://img5.mtime.cn/mg/2021/11/29/161039.94816339_285X160X4.jpg",
                "图片一"
            )
        )
        banners.add(
            Banners(
                "http://img5.mtime.cn/mg/2021/11/29/161013.78631900_285X160X4.jpg",
                "图片二"
            )
        )
        banners.add(
            Banners(
                "http://img5.mtime.cn/mg/2021/11/29/150044.85598938_285X160X4.jpg",
                "图片三"
            )
        )
        banners.add(
            Banners(
                "http://img5.mtime.cn/mg/2021/11/29/145340.97660451_285X160X4.jpg",
                "图片四"
            )
        )

        // 自己把万能的无限轮播看一下
        binding.bannerView.setAdapter(object : BannerAdapter() {
            override fun getView(position: Int, convertView: View?): View {
                var convertV = convertView
                if (convertV == null) {
                    convertV = ImageView(this@BannerActivity)
                }
                (convertV as ImageView).scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(this@BannerActivity).load(
                    banners[position].url
                ).into(convertV as ImageView?)
                return convertV
            }

            override fun getCount(): Int = banners.size

            override fun getBannerDesc(position: Int): String? {
                return banners[position].name
            }
        })

        binding.bannerView.setOnBannerItemClickListener(this)
        // 开启滚动
        // 开启滚动
        binding.bannerView.startRoll()
    }

    override fun click(position: Int) {
        Toast.makeText(this, "banner ${banners[position].name}", Toast.LENGTH_LONG).show()
    }
}