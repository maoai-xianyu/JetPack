package com.mao.jetpack.ui.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mao.jetpack.R
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


        /*val findNavController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(findNavController)*/


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val findNavController = navHostFragment!!.findNavController()
        binding.navView.setupWithNavController(findNavController)
    }
}