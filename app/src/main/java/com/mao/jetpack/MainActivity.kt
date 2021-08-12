package com.mao.jetpack

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        //setCustomDensity(this, this.application)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        // 停止traceview
        //Debug.startMethodTracing()
    }


    // 字节跳动
    fun setCustomDensityOne(activity: Activity, application: Application) {
        // displayMetrics
        val displayMetrics = application.resources.displayMetrics
        // UI设计师以360dp为基准
        val targetDensity = displayMetrics.widthPixels / 360
        val targetDensityDpi = 160 * targetDensity
        displayMetrics.density = targetDensity.toFloat()
        displayMetrics.densityDpi = targetDensityDpi

    }


    private var sNoncompatDensity = 0f // 系统的Density
    private var sNoncompatScaleDensity = 0f // 系统的ScaledDensity


    private fun setCustomDensity(activity: Activity, application: Application) {
        val appDisplayMetrics = application.resources.displayMetrics
        if (sNoncompatDensity.toInt() == 0) {
            // 系统的Density
            sNoncompatDensity = appDisplayMetrics.density
            // 系统的ScaledDensity
            sNoncompatScaleDensity = appDisplayMetrics.scaledDensity
            // 监听在系统设置中切换字体
            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(newConfig: Configuration) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaleDensity = application.resources.displayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {}
            })
        }
        // 此处以360dp的设计图作为例子
        val targetDensity = (appDisplayMetrics.widthPixels / 360).toFloat()
        val targetScaledDensity: Float =
            targetDensity * (sNoncompatScaleDensity / sNoncompatDensity)

        val targetDensityDpi = (160 * targetDensity).toInt()

        // application 中的也改掉
        appDisplayMetrics.density = targetDensity
        appDisplayMetrics.scaledDensity = targetScaledDensity
        appDisplayMetrics.densityDpi = targetDensityDpi

        // 改掉 activity 中 的 density
        val activityDisplayMetrics = activity.resources.displayMetrics
        activityDisplayMetrics.density = targetDensity
        activityDisplayMetrics.scaledDensity = targetScaledDensity
        activityDisplayMetrics.densityDpi = targetDensityDpi
    }

}