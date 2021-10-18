package com.mao.jetpack.ui.button

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityButtonShapeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}