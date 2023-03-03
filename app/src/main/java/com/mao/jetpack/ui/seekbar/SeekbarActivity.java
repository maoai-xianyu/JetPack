package com.mao.jetpack.ui.seekbar;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.databinding.ActivitySeekBarBinding;

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description
 */
public class SeekbarActivity extends AppCompatActivity {

    private ActivitySeekBarBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySeekBarBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

    }

}
