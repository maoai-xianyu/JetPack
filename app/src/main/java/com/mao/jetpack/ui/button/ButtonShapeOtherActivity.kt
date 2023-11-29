package com.mao.jetpack.ui.button

import android.R
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue.applyDimension
import androidx.appcompat.app.AppCompatActivity
import com.mao.jetpack.databinding.ActivityButtonShapeBinding
import com.mao.jetpack.databinding.ActivityButtonShapeOtherBinding

/**
 *
 * @author zhangkun
 * @time 2021/10/18 12:04
 * @Description
 */
class ButtonShapeOtherActivity : AppCompatActivity() {

    lateinit var binding: ActivityButtonShapeOtherBinding

    var wish = false
    var wishOhter = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityButtonShapeOtherBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tv.setOnClickListener {
            if (wish) {
                wish = false
                binding.tv.text = "想看"
                binding.tv.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tv.setBackgroundColor(Color.parseColor("#FAAF00"))
                binding.shadowLayout.setShadowColor(Color.parseColor("#2EFAAF00"))
                binding.shadowLayout.setStrokeColor(Color.parseColor("#2EFAAF00"))

            } else {
                wish = true
                binding.tv.text = "已想看"
                binding.tv.setTextColor(Color.parseColor("#666666"))
                binding.tv.setBackgroundColor(Color.parseColor("#FFFFFF"))
                binding.shadowLayout.setShadowColor(Color.parseColor("#12000000"))
                binding.shadowLayout.setStrokeColor(Color.parseColor("#12000000"))
            }
        }

        binding.tvMMM.setTextColor(
            ColorStateList(
                arrayOf(
                    intArrayOf(
                        R.attr.state_selected
                    ), IntArray(0)
                ), intArrayOf(Color.parseColor("#d54544"), Color.parseColor("#696969"))
            )
        )


        kotlin.runCatching {  }

    }

}