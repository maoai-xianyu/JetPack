package com.mao.jetpack.bannerloop;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.mao.jetpack.R;

public class MaoyanAdxViewPagerIndicator extends View implements ViewPagerIndicator {

    private ViewPager viewPager;
    private int currentItem;
    private float positionOffset;
    private final Paint bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint selectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int scrollState;
    private int count;

    public MaoyanAdxViewPagerIndicator(Context context) {
        this(context, null);
    }

    public MaoyanAdxViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaoyanAdxViewPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int defaultBgColor = context.getResources().getColor(R.color.maoyan_adx_indicator_default_bg_color);
        int defaultSelectedColor = context.getResources().getColor(R.color.maoyan_adx_indicator_default_selected_color);
        TypedArray a = context
            .obtainStyledAttributes(attrs, R.styleable.MaoyanAdxViewPagerIndicator, defStyleAttr, 0);
        bgPaint.setColor(a.getColor(R.styleable.MaoyanAdxViewPagerIndicator_maoyan_adx_indicator_bg_color, defaultBgColor));
        selectedPaint.setColor(a.getColor(R.styleable.MaoyanAdxViewPagerIndicator_maoyan_adx_indicator_selected_color,
            defaultSelectedColor));
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (viewPager == null) {
            return;
        }
        count = viewPager.getAdapter().getCount();
        if (count == 0) {
            return;
        }
        final int paddingLeft = getPaddingLeft();
        final float pageWidth = (getWidth() - paddingLeft - getPaddingRight()) / (1f * count);
        final float left = paddingLeft + pageWidth * (currentItem + positionOffset);
        final float right = left + pageWidth;
        final float top = getPaddingTop();
        final float bottom = getHeight() - getPaddingBottom();
        if (right > getWidth()) {
            canvas.drawRect(left, top, getWidth(), bottom, selectedPaint);
            canvas.drawRect(getPaddingLeft(), top, right - getWidth(), bottom, selectedPaint);
        } else {
            canvas.drawRect(left, top, right, bottom, selectedPaint);
        }
        canvas.drawRect(getPaddingLeft(), top, getWidth() - getPaddingRight(), bottom, bgPaint);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (count > 0) {
            currentItem = position % count;
            this.positionOffset = positionOffset;
            invalidate();
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (scrollState == ViewPager.SCROLL_STATE_IDLE && count > 0) {
            currentItem = position % count;
            positionOffset = 0;
            invalidate();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        scrollState = state;
    }

    @Override
    public void setViewPager(ViewPager viewPager) {
        if (viewPager == null) {
            return;
        }
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(this);
        invalidate();
    }

    @Override
    public void notifyDataSetChanged() {
        invalidate();
    }
}
