package com.mao.jetpack.ui.button

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue.applyDimension
import androidx.appcompat.app.AppCompatActivity
import com.mao.jetpack.databinding.ActivityButtonShapeBinding

/**
 *
 * @author zhangkun
 * @time 2021/10/18 12:04
 * @Description
 */
class ButtonShapeActivity : AppCompatActivity() {

    lateinit var binding: ActivityButtonShapeBinding

    var wish = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityButtonShapeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*binding.tv.setOnClickListener {
            if (wish) {
                wish = false
                binding.tv.text = "想看"
                binding.tv.setTextColor(Color.parseColor("#FFFFFF"))
                binding.tv.setBackgroundColor(Color.parseColor("#FAAF00"))
                binding.shadowLayout.setmShadowColor(Color.parseColor("#2EFAAF00"))

            } else {
                wish = true
                binding.tv.text = "已想看"
                binding.tv.setTextColor(Color.parseColor("#666666"))
                binding.tv.setBackgroundColor(Color.parseColor("#FFFFFF"))
                binding.shadowLayout.setmShadowColor(Color.parseColor("#0d000000"))
            }
        }
*/

    }


    fun dp2px(value: Float): Int {
        return applyDimension(1, value, Resources.getSystem().displayMetrics).toInt()
    }

}