package com.mao.jetpack.ui.reflect;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.R;

/**
 * @author zhangkun
 * @time 2022/4/12 10:12
 * @Description
 */
public class ReflectSecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reflect_second);
    }
}
