package com.mao.jetpack.ui.motionlayout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mao.jetpack.databinding.ActivityMotionLayoutBinding

/**
 *
 * @author zhangkun
 * @time 2021/4/27 8:28 AM
 * @Description
 */
class MotionLayoutActivity : AppCompatActivity() {


    lateinit var binding: ActivityMotionLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotionLayoutBinding.inflate(layoutInflater)
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

        binding.tvM2.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity,
                ConstraintSetExample::class.java))
        }

        binding.tvM3.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity,
                MotionActivity::class.java))
        }

        binding.tvM4.setOnClickListener {
            startActivity(Intent(this@MotionLayoutActivity,
                MotionSampleActivity::class.java))
        }
    }

}