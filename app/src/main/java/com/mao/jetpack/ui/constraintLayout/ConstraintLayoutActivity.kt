package com.mao.jetpack.ui.constraintLayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mao.jetpack.databinding.ActivityConstraintlayoutBinding

/**
 *
 * @author zhangkun
 * @time 2021/4/14 7:55 AM
 * @Description
 */
class ConstraintLayoutActivity : AppCompatActivity() {


    private lateinit var rootBinding: ActivityConstraintlayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootBinding = ActivityConstraintlayoutBinding.inflate(layoutInflater)
        setContentView(rootBinding.root)

    }
}