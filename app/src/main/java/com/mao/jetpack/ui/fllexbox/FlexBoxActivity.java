package com.mao.jetpack.ui.fllexbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;
import com.mao.jetpack.R;
import com.mao.jetpack.databinding.ActivityFlexBoxBinding;
import com.mao.jetpack.widget.FlexBoxLayoutMaxLines;
import com.mao.jetpack.widget.flexbox.MovieSingleViewTypeFlowLayout;

import java.util.ArrayList;
import java.util.List;

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

        List<String> strings = new ArrayList<>();
        strings.add("项目一");
        strings.add("项目二");
        strings.add("项目三");
        strings.add("项目四");
        strings.add("项目五");
        strings.add("项目六");
        strings.add("项目七");
        strings.add("项目八");

        FlowLayoutAdapter mAdapter = new FlowLayoutAdapter(strings, R.layout.movie_view_flowlayout_item_textview);
        binding.movieListFlowLayout.setAdapter(mAdapter);

    }


    private static final class FlowLayoutAdapter extends MovieSingleViewTypeFlowLayout.Adapter<String> {

        FlowLayoutAdapter(List<String> data, int layoutId) {
            super(data, layoutId);
        }

        @Override
        public void onBindViewHolder(MovieSingleViewTypeFlowLayout.ViewHolder holder, int position) {
            TextView textView = holder.findView(R.id.tv_fillet_bg);
            textView.setText((CharSequence) getItem(position));
        }
    }

}
