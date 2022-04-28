package com.mao.jetpack.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author zhangkun
 * @time 2022/4/28 14:11
 * @Description
 */
class ArcImage extends AppCompatImageView {


    public ArcImage(@NonNull Context context) {
        super(context);
    }

    public ArcImage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ArcImage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


    }
}
