package com.mao.jetpack.ui.fllexbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;
import com.mao.jetpack.databinding.ActivityFlexBoxBinding;
import com.mao.jetpack.widget.FlexBoxLayoutMaxLines;

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description
 */
public class FlexBoxActivity extends AppCompatActivity {

    private ActivityFlexBoxBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFlexBoxBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.ivArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.hotContent.getMaxLines() != FlexboxLayout.NOT_SET) {
                    binding.hotContent.setMaxLine(FlexboxLayout.NOT_SET);
                } else {
                    binding.hotContent.setMaxLine(1);
                }
                binding.ivArrowDown.setRotation(binding.ivArrowDown.getRotation() == 0 ? 180 : 0);
            }
        });


        binding.hotContent
                .setOnLinesChangedListener(new FlexBoxLayoutMaxLines.OnLinesChangedListener() {
                    @Override
                    public void onLinesChanged(int lines) {
                        binding.ivArrowDown.setVisibility(lines > 1 ? View.VISIBLE : View.GONE);
                    }
                });

    }

}
