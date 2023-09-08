package com.mao.jetpack.ui.text;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.R;
import com.mao.jetpack.databinding.ActivityOneBinding;

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description  多海报正确显示
 */
public class ActivityOneActivity extends AppCompatActivity {

    private ActivityOneBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOneBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        View root =  View.inflate(this,R.layout.activity_one_item_add, null);
        binding.content.addView(root,0);



    }


}
