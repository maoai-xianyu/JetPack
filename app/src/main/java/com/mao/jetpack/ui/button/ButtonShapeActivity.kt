package com.mao.jetpack.ui.button

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue.applyDimension
import androidx.appcompat.app.AppCompatActivity
import com.mao.jetpack.databinding.ActivityButtonShapeBinding

import com.mao.jetpack.widget.shadow.Util


/**
 *
 * @author zhangkun
 * @time 2021/10/18 12:04
 * @Description
 */
class ButtonShapeActivity : AppCompatActivity() {

    lateinit var binding: ActivityButtonShapeBinding

    var wish = false
    var wishLook = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityButtonShapeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tv.setOnClickListener {
            if (wish) {
                wish = false
                binding.tv.text = "想看"
                binding.tv.setTextColor(Color.parseColor("#FFFFFF"))
                binding.shadowLayout.setmBackGroundColor(Color.parseColor("#FAAF00"))
                binding.shadowLayout.setmShadowColor(Util.convertColor("#FAAF00"))

            } else {
                wish = true
                binding.tv.text = "已想看"
                binding.tv.setTextColor(Color.parseColor("#666666"))
                binding.shadowLayout.setmBackGroundColor(Color.parseColor("#FFFFFF"))
                binding.shadowLayout.setmShadowColor(Util.convertColor("12", "#000000"))
            }
        }

        binding.tvChange.setOnClickListener {
            if (wishLook) {
                wishLook = false
                binding.tvChange.text = "想看"
                binding.tvChange.setTextColor(Color.parseColor("#FFFFFF"))
                binding.card.setCardBackgroundColor(Color.parseColor("#faaf00"))

            } else {
                wishLook = true
                binding.tvChange.text = "已想看"
                binding.tvChange.setTextColor(Color.parseColor("#666666"))
                binding.card.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            }
        }

    }


    fun dp2px(value: Float): Int {
        return applyDimension(1, value, Resources.getSystem().displayMetrics).toInt()
    }

}