package com.mao.jetpack.ui.motionlayout

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionLayout.DEBUG_SHOW_PATH
import com.mao.jetpack.R

class HenCoderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hen_coder)
        findViewById<Button>(R.id.showDebug).setOnClickListener {
            findViewById<MotionLayout>(R.id.motionLayout).setDebugMode(DEBUG_SHOW_PATH)
        }
    }
}