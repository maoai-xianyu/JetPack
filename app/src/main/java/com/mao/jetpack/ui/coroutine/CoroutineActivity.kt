package com.mao.jetpack.ui.coroutine

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.mao.jetpack.R
import com.mao.jetpack.databinding.ActivityCoroutineBinding
import com.mao.jetpack.model.ImageModel

/**
 *
 * @author zhangkun
 * @time 2022/3/25 15:08
 * @Description
 */
class CoroutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoroutineBinding

    // 创建 viewModel
    private val viewModel by viewModels<CoroutineViewModel>()

    val jokeAdapter: JokeAdapter by lazy {
        JokeAdapter()
    }

    private lateinit var viewModels: CoroutineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine)

        // 这样也可以创建viewModel
        //viewModels = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(CoroutineViewModel::class.java)
        binding.viewModel = viewModel

        val imageModel =
            ImageModel("https://img5.mtime.cn/pi/2022/01/24/144146.59288160_1000X1000.jpg", "美女")
        binding.imageModel = imageModel
        binding.localUrl = R.drawable.ic_pyramid


        // 网络请求单个笑话
        viewModel.getSingleJoke()

        viewModel.jokeOne.observe(this) {
            binding.tvJoke.text = it.content
        }


        // 获取笑话列表
        viewModel.getJokeList()


        binding.rvJoke.adapter = jokeAdapter;

        viewModel.jokeList.observe(this) {
            jokeAdapter.setDataList(it)
        }

        // 将图片切割成 1/4
        viewModel.getImage4x4()

        viewModel.image4x4First.observe(this) {

            Glide.with(this).load(it.Bitmap2Bytes())
                .override(60, 60)
                .into(binding.iv4x4)
            // 两种方式都可以
            //binding.iv4x4.setImageDrawable(BitmapDrawable(resources, it))
        }


        viewModel.getImage9x9()

        viewModel.image9x9Last.observe(this) {
           /* Glide.with(this).load(BitmapDrawable(resources, it))
                .override(60, 60)
                .into(binding.iv9x9)*/
            binding.iv9x9.setImageDrawable(BitmapDrawable(resources, it))
        }

    }

}