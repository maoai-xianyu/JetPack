package com.mao.jetpack.ui.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mao.jetpack.databinding.ActivityNvBinding

/**
 *
 * @author zhangkun
 * @time 2021/4/14 8:45 PM
 * @Description
 */
class NavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNvBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}