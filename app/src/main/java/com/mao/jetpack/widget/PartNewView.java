package com.mao.jetpack.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.mao.jetpack.R;

/**
 * @author shuxin.wei email:weishuxin@maoyan.com
 * @version v1.0.0
 * @date 2019-08-12
 * <p>
 * 自定义观点布局
 */
@Keep
public class PartNewView extends ConstraintLayout implements View.OnClickListener {
    private Paint mPaint;
    private Path mLeftPath;
    private Path mRightPath;

    /**
     * View的宽
     */
    private float mTotalWidth;
    /**
     * View的高
     */
    private float mTotalHeight;
    /**
     * 拐角半径
     */
    private float mCornerRadius = 10.0f;
    /**
     * 梯形斜边角度
     */
    private float mAngle = 15.0f;

    /**
     * 左侧颜色
     */
    private int mLeftColor = 0xFFFF4141;

    /**
     * 右侧颜色
     */
    private int mRightColor = 0xFF3E72FF;

    /**
     * 背景色
     */
    private int mBackGroundColor = Color.TRANSPARENT;

    /**
     * left right rate
     */
    private float mRate;

    /**
     * 默认宽度
     */
    private static final int DEFAULT_WIDTH = 800;

    /**
     * 默认高度
     */
    private static final int DEFAULT_HEIGHT = 200;

    /**
     * 中间间隔 gap转换后的PX
     */
    private float mGapPx;

    /**
     * 阴影半径
     */
    private float mShadowRadius;
    /**
     * 阴影Y轴偏移量
     */
    private float mElevation;

    private float mPaddingLeft;
    private float mPaddingTop;
    private float mPaddingBottom;
    private float mPaddingRight;
    private int shadow;


    public PartNewView(Context context) {
        this(context, null);
    }

    public PartNewView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PartNewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }


    public PartNewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PartNewView);
        mRate = typedArray.getFloat(R.styleable.PartNewView_rate_new, 0.5f);
        //左边梯形和右边梯形的间隔
        mGapPx = typedArray.getDimension(R.styleable.PartNewView_gap_new, 0);
        typedArray.recycle();
        //继承ViewGroup时，不设置背景，不会执行draw方法
        setBackgroundColor(Color.TRANSPARENT);
        //禁用硬件加速，否则阴影等细节不起作用
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mShadowRadius = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                getResources().getDisplayMetrics()) / 2);
        mElevation = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
                getResources().getDisplayMetrics()) / 2);
        initPain();
        //初始化Padding
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
//        setClipToOutline(false);
//        setElevation(10);
//        setTranslationZ(10);
        setOnClickListener(this);
    }

    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();
    }

    @Override
    protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) {
        super.onSizeChanged(width, height, oldWidth, oldHeight);
        mTotalWidth = width;
        mTotalHeight = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制画布背景
        canvas.drawColor(mBackGroundColor);
        //绘制左边
        if (Float.compare(mRate, 0f) != 0) {
            initPath(true);
            @SuppressLint("DrawAllocation")
            LinearGradient mLeftColorLg = new LinearGradient(0, 0, getWidth(), 0,
                    new int[]{0xFFE65060, 0xFFF03535}, null, Shader.TileMode.CLAMP);
            mPaint.setShader(mLeftColorLg);
            //绘制阴影
            //mPaint.setShadowLayer(mShadowRadius, 0, mElevation, getDarkerColor(mLeftColor));
            canvas.drawPath(mLeftPath, mPaint);
        }
        //绘制右边
        if (Float.compare(mRate, 1.0f) != 0) {
            initPath(false);
            @SuppressLint("DrawAllocation")
            LinearGradient mRightColorLg = new LinearGradient(0, 0, getWidth(), 0,
                    new int[]{0xFF3F9BCC, 0xFF2E9AE6}, null, Shader.TileMode.CLAMP);
            mPaint.setShader(mRightColorLg);
            //绘制阴影
            //mPaint.setShadowLayer(mShadowRadius, 0, mElevation, getDarkerColor(mRightColor));
            canvas.drawPath(mRightPath, mPaint);
        }
    }

    private void initPain() {
        mPaint = new Paint();
        //设置阴影效果不行
//        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.INNER));
        //抗锯齿
        mPaint.setAntiAlias(true);
        //设置拐角为圆角
        CornerPathEffect corEffect = new CornerPathEffect(mCornerRadius);
        mPaint.setPathEffect(corEffect);
        mPaint.setStyle(Paint.Style.FILL);
        //设置线的头是圆角的
//        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    public int getDarkerColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = hsv[1] - 0.5f;
        hsv[2] = hsv[2] + 0.5f;
        return Color.HSVToColor(hsv);
    }

    private void initPath(boolean isLeft) {
        float width = mTotalWidth;
        float height = mTotalHeight;
        //梯形长边和短边差值
        float mDifferentLength = calculateDiffLength(height);
        //圆的半径
        float radius = (height - mPaddingTop - mPaddingBottom) / 2;
        //拐角的半径的一半，
        float halfCornerRadius = mCornerRadius / 2;
        //分给每个梯形的宽度
        float half = mDifferentLength / 2 - mGapPx;
        float trapezoidMaxLength;
        float trapezoidShortLength;
        if (isLeft) {
            //梯形长边
            trapezoidMaxLength = width * mRate + half;
        } else {
            //梯形长边
            trapezoidMaxLength = width * (1 - mRate) + half;
        }
        //梯形短边
        trapezoidShortLength = trapezoidMaxLength - mDifferentLength;

        //等于1或者等于0可以显示单个View
        if (Float.compare(mRate, 1.0f) != 0 && Float.compare(mRate, 0f) != 0) {
            //最短不能短于圆的半径
            float diameter = radius * 2;
            if (trapezoidShortLength < diameter) {
                trapezoidShortLength = diameter;
                trapezoidMaxLength = trapezoidShortLength + mDifferentLength;
            }
            //最长不能长于两个圆的距离，左侧是整圆，所以是3个半径
            float maxLength = width - 3 * radius;
            if (trapezoidMaxLength > maxLength) {
                trapezoidMaxLength = maxLength;
                trapezoidShortLength = trapezoidMaxLength - mDifferentLength;
            }
        }
        //绘制单个，长边不能超出View
        else {
            trapezoidMaxLength -= mDifferentLength / 2;
            trapezoidShortLength -= mDifferentLength / 2;
        }

        if (isLeft) {
            mLeftPath = new Path();
            //左侧圆
            float scaleSizeLeft = 0;
            mLeftPath.addCircle(radius + mPaddingLeft - scaleSizeLeft,
                    radius + mPaddingTop + scaleSizeLeft,
                    radius + scaleSizeLeft, Path.Direction.CW);
            //右侧梯形
            mLeftPath.moveTo(radius + mPaddingLeft - scaleSizeLeft, mPaddingTop);
            mLeftPath.lineTo(trapezoidMaxLength, mPaddingTop);
            mLeftPath.lineTo(trapezoidShortLength, height - mPaddingBottom);
            mLeftPath.lineTo(radius - halfCornerRadius + mPaddingLeft - scaleSizeLeft, height - mPaddingBottom);
            //如果是Style.FILL的话，不设置close,也没有区别，可是如果是STROKE模式， 如果不设置close,图形不封闭。当然，你也可以不设置close，再添加一条线，效果一样。
            mLeftPath.close();
        } else {
            mRightPath = new Path();
            //右侧圆
            mRightPath.addCircle(width - radius - mPaddingRight, radius + mPaddingTop, radius, Path.Direction.CW);
            //右侧梯形
            mRightPath.moveTo(width - radius + halfCornerRadius - mPaddingRight, mPaddingTop);
            mRightPath.lineTo(width - radius + halfCornerRadius - mPaddingRight, height - mPaddingBottom);
            mRightPath.lineTo(width - trapezoidMaxLength, height - mPaddingBottom);
            mRightPath.lineTo(width - trapezoidShortLength, mPaddingTop);
            //如果是Style.FILL的话，不设置close,也没有区别，可是如果是STROKE模式， 如果不设置close,图形不封闭。当然，你也可以不设置close，再添加一条线，效果一样。
            mRightPath.close();
        }
    }

    /**
     * 计算梯形长边和短边的差值
     */
    public float calculateDiffLength(float height) {
        return (float) ((height - mPaddingTop - mPaddingBottom - shadow * 2) * Math.abs(Math.tan(Math.toRadians(mAngle))));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获取宽-测量规则的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        MarginLayoutParams layoutParams = (MarginLayoutParams) getLayoutParams();
        int widthSize = MeasureSpec.getSize(widthMeasureSpec) - layoutParams.leftMargin - layoutParams.rightMargin;

        // 获取高-测量规则的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec) - layoutParams.topMargin - layoutParams.bottomMargin;

        // 当模式是AT_MOST（即wrap_content）时设置默认值
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
            // 宽 / 高任意一个模式为AT_MOST（即wrap_content）时，都设置默认值
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(DEFAULT_WIDTH, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, DEFAULT_HEIGHT);
        }
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        //每次重新计算Padding，Y轴方向Padding 留白要是阴影半径的2倍，否则效果不好
        shadow = (int) (mShadowRadius * 2);
        super.setPadding(left + shadow, top + shadow, right + shadow, bottom + shadow);
        mPaddingLeft = getPaddingLeft();
        mPaddingTop = getPaddingTop();
        mPaddingBottom = getPaddingBottom();
        mPaddingRight = getPaddingRight();
    }

    public void setRate(int left, int right) {
        if (right == 0) {
            this.mRate = 1;
            return;
        }
        float rate = (float) left / right;
        if (rate > 1) {
            rate = 1;
        } else if (rate < 0) {
            rate = 0;
        }
        this.mRate = rate;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }

    public int getLeftColor() {
        return mLeftColor;
    }

    public int getRightColor() {
        return mRightColor;
    }

    public void setAngle(float mAngle) {
        this.mAngle = mAngle;
    }

    public void setBackGroundColor(int mBackGroundColor) {
        this.mBackGroundColor = mBackGroundColor;
    }

    @Override
    public void setElevation(float elevation) {
        this.mElevation = elevation;
    }

    @Override
    public void onClick(View v) {
    }

    public void setWidthHeight(int newWidth, int newHeight) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = newWidth;
        params.height = newHeight;
        setLayoutParams(params);
    }

    @Keep
    public void setWidth(int newWidth) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = newWidth;
        setLayoutParams(params);
    }

    @Keep
    public void setHeight(int newHeight) {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = newHeight;
        setLayoutParams(params);
    }
}
