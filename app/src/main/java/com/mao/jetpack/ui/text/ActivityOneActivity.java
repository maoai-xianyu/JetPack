package com.mao.jetpack.ui.text;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.R;
import com.mao.jetpack.databinding.ActivityOneBinding;

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description 多海报正确显示
 */
public class ActivityOneActivity extends AppCompatActivity {

    private ActivityOneBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOneBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        View root = View.inflate(this, R.layout.activity_one_item_add, null);
        binding.content.addView(root, 0);


        String text = "这是第一行文本，图像接着文本，然后这是第二行文本。";
        SpannableString spannableString = new SpannableString(text);

// 替换R.drawable.your_image_resource为你的图片资源
        Drawable drawable = getResources().getDrawable(R.drawable.ic_pyramid);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

// 创建一个ImageSpan将图片插入到文本中
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BOTTOM);

// 将ImageSpan应用于第一行文本的末尾
        spannableString.setSpan(imageSpan, text.indexOf("图像"), text.indexOf("文本") + 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

// 设置文本的最大行数为2，并将文本内容设置为SpannableString
        binding.tv.setMaxLines(2);
        binding.tv.setText(spannableString);

    }


}
