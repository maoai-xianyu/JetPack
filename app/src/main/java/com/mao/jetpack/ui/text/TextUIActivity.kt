package com.mao.jetpack.ui.text

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.mao.jetpack.databinding.ActivityTextUiBinding

/**
 *
 * @author zhangkun
 * @time 2022/10/8 20:21
 * @Description
 */
class TextUIActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextUiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTextUiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var text = "双向绑定双向向中国\n香港绑定双向绑定绑定绑定澳门"
        binding.tvBind.text = text
        binding.tvBind.post {


            val lineCount = binding.tvBind.lineCount

            Log.e("-----", "lineCount $lineCount")

            val ellipsisCount = binding.tvBind.layout.getEllipsisCount(lineCount - 1)
            if (ellipsisCount > 0) {
                Log.i("-----", "text 前 $text")
                text = text.replace("\n","")
                Log.d("-----", "text 后 $text")

                binding.tvBind.text = text
            }

        }
    }
}