package com.mao.jetpack.ui.coroutine

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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

    private lateinit var viewModels: CoroutineViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine)

        // 这样也可以创建viewModel
        //viewModels = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory(application)).get(CoroutineViewModel::class.java)
        binding.viewModel = viewModel

        val imageModel =
            ImageModel("http://img5.mtime.cn/pi/2022/01/24/144146.59288160_1000X1000.jpg", "美女")
        binding.imageModel = imageModel


        viewModel.jokeList.observe(this) {

        }


        viewModel.jokeOne.observe(this){

        }

    }

}