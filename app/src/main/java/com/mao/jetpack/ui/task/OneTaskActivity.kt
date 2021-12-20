package com.mao.jetpack.ui.task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mao.jetpack.databinding.ActivityOneTaskBinding
import com.mao.jetpack.utils.Logger

/**
 *
 * @author zhangkun
 * @time 2021/9/14 20:44
 * @Description
 */
class OneTaskActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityOneTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnJumpSelfOne.setOnClickListener {

            val intent = Intent(this, OneTaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)

        }

        binding.btnJumpSelfTwo.setOnClickListener {
            val intent = Intent(this, OneTaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }


        binding.btnJumpSelfThree.setOnClickListener {
            val intent = Intent(this, OneTaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }


        binding.btnJumpSelfFour.setOnClickListener {

            val intent = Intent(this, OneTaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)

        }


        binding.btnJumpTwoOne.setOnClickListener {

            val intent = Intent(this, TwoTaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)

        }

        binding.btnJumpTwoTwo.setOnClickListener {
            val intent = Intent(this, TwoTaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }


        binding.btnJumpTwoThree.setOnClickListener {
            val intent = Intent(this, TwoTaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }


        binding.btnJumpTwoFour.setOnClickListener {

            val intent = Intent(this, TwoTaskActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Logger.error("onDestroy")
    }
}