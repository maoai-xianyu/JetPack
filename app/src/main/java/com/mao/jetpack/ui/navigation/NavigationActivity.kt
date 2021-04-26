package com.mao.jetpack.ui.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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

        // 方法一：
        /*val findNavController = findNavController(R.id.nav_host_fragment)
        binding.navView.setupWithNavController(findNavController)*/

        // 方法二：
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        val findNavController = navHostFragment!!.findNavController()
        binding.navView.setupWithNavController(findNavController)

        // 方法一： 用代码配置 创建 NavHostFragment，不再布局文引入的话 针对
        // android:name="androidx.navigation.fragment.NavHostFragment" 的配置
        /*val finalHost = NavHostFragment.create(R.navigation.nv_navigation)
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment,finalHost)
            .setPrimaryNavigationFragment(finalHost)
            .commit()*/

    }

    // 方法二： 代码配置
    /*override fun onSupportNavigateUp(): Boolean {
        val navHostFragment  = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        return NavHostFragment.findNavController(navHostFragment!!).navigateUp()
    }*/
}