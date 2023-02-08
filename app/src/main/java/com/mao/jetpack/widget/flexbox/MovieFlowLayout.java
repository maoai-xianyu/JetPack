package com.mao.jetpack.widget.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.mao.jetpack.R;


/**
 * @ Author: Wei Xiaomin
 * @ Email: weixiaomin02@maoyan.com
 * @ Date: 2017/10/20
 * @ Description: 流式布局，类似于标签的功能
 */
public class MovieFlowLayout extends ViewGroup {
    /**
     * 每个item纵向间距
     */
    protected int mVerticalSpacing;
    /**
     * 每个item横向间距
     */
    protected int mHorizontalSpacing;

    public MovieFlowLayout(Context context) {
        this(context, null);
    }

    public MovieFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        //获得我们所定义的自定义样式属性
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MovieFlowLayout, defStyle, 0);
        for (int i = 0; i < a.getIndexCount(); i++) {

            int attr = a.getIndex(i);
            if (attr == R.styleable.MovieFlowLayout_verticalSpacing) {
                mVerticalSpacing = a.getDimensionPixelSize(R.styleable.MovieFlowLayout_verticalSpacing, 5);
            } else if (attr == R.styleable.MovieFlowLayout_horizontalSpacing) {
                mHorizontalSpacing = a.getDimensionPixelSize(R.styleable.MovieFlowLayout_horizontalSpacing, 10);
            }
        }
        a.recycle();
    }

    //wxm:设置子View的间距
    public void setSpacing(int verticalSpacing, int horizontalSpacing) {
        mVerticalSpacing = verticalSpacing;
        mHorizontalSpacing = horizontalSpacing;
    }

    /**
     * 负责设置子控件的测量模式和大小 根据所有子控件设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;
        int height = 0;

        int row = 0; // The row counter.
        int rowWidth = 0; // Calc the current row width.
        int rowMaxHeight = 0; // Calc the max tag height, in current row.

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();

            if (child.getVisibility() != GONE) {
                rowWidth += childWidth;
                if (rowWidth > widthSize) { // Next line.
                    rowWidth = childWidth; // The next row width.
                    height += rowMaxHeight + mVerticalSpacing;
                    rowMaxHeight = childHeight; // The next row max height.
                    row++;
                } else { // This line.
                    rowMaxHeight = Math.max(rowMaxHeight, childHeight);
                }
                rowWidth += mHorizontalSpacing;
            }
        }
        // Account for the last row height.
        height += rowMaxHeight;

        // Account for the padding too.
        height += getPaddingTop() + getPaddingBottom();

        // If the tags grouped in one row, set the width to wrap the tags.
        if (row == 0) {
            width = rowWidth;
            width += getPaddingLeft() + getPaddingRight();
        } else { // If the tags grouped exceed one line, set the width to match the parent.
            width = widthSize;
        }

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width,
                heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int parentLeft = getPaddingLeft();
        final int parentRight = r - l - getPaddingRight();
        final int parentTop = getPaddingTop();

        int childLeft = parentLeft;
        int childTop = parentTop;

        int rowMaxHeight = 0;

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            final int width = child.getMeasuredWidth();
            final int height = child.getMeasuredHeight();

            if (child.getVisibility() != GONE) {
                if (childLeft + width > parentRight) { // Next line
                    childLeft = parentLeft;
                    childTop += rowMaxHeight + mVerticalSpacing;
                    rowMaxHeight = height;
                } else {
                    rowMaxHeight = Math.max(rowMaxHeight, height);
                }
                child.layout(childLeft, childTop, childLeft + width, childTop + height);

                childLeft += width + mHorizontalSpacing;
            }
        }
    }
}
