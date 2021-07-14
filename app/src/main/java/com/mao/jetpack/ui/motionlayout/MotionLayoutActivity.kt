package com.mao.jetpack.ui.motionlayout

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.TransitionManager
import com.mao.jetpack.databinding.ActivityMotionlayoutBinding
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2021/4/27 8:28 AM
 * @Description
 */
class MotionLayoutActivity : AppCompatActivity() {


    lateinit var binding: ActivityMotionlayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotionlayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvObjectAn1.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity,
                MLObjectAnimator1Activity::class.java))
        }

        binding.tvObjectAn2.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity,
                MLObjectAnimator2Activity::class.java))
        }

        binding.tvM1.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity,
                GoActivity::class.java))
        }
    }

}