package com.mao.jetpack.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @package: com.designpatterns.time
 * @author： 1668626317@qq.com
 * @describe：
 * @time： 2021/5/10 11:10
 */
public class MyView extends View {
    private static final String TAG = "MyView";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    Path p1 = new Path();
    Path p2 = new Path();
    RectF r1 = new RectF();
    RectF r2 = new RectF();


    private String left ="这个这个这个";
    private String right = "那个那个那个";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    private OnTouchCallBack touchCallBack;

    public void setTouchCallBack(OnTouchCallBack touchCallBack) {
        this.touchCallBack = touchCallBack;
    }

    public interface OnTouchCallBack{
        void left();
        void right();
    }
    public void setLeft(String left) {
        this.left = left;
        invalidate();
    }
    public void setRight(String right) {
        this.right = right;
        invalidate();
    }
    public void setText(String left,String right){
        setLeft(left);
        setLeft(right);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        p1.computeBounds(r1,true);
        p2.computeBounds(r2,true);
        if (r1.contains(event.getX(),event.getY())){
            if (touchCallBack!=null){
                touchCallBack.left();
            }
        }else if (r2.contains(event.getX(),event.getY())){
            if (touchCallBack!=null){
                touchCallBack.right();
            }
        }

        return super.onTouchEvent(event);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        paint.setColor(Color.parseColor("#F3B435"));
//        canvas.clipRect(0, 0, getWidth() / 2, getHeight());

        p1.moveTo(0, 0);
        p1.lineTo((getWidth() / 2) + 10, 0);
        p1.lineTo((getWidth() / 2) - 20, getHeight());
        p1.lineTo(0, getHeight());
        canvas.clipPath(p1);
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), 100, 100, paint);
        canvas.restore();

        canvas.save();
        paint.setColor(Color.parseColor("#9FD25BF3"));

        p2.moveTo((getWidth() / 2) + 20, 0);
        p2.lineTo(getWidth(), 0);
        p2.lineTo(getWidth(), getHeight());
        p2.lineTo((getWidth() / 2) - 10, getHeight());
        canvas.clipPath(p2);
        canvas.drawRoundRect(0, 0, getWidth(), getHeight(), 100, 100, paint);
        canvas.restore();

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);


        Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();
        float base = (fontMetricsInt.descent - fontMetricsInt.ascent) / 2 - fontMetricsInt.descent;
        textPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(left, getWidth() / 4, getHeight() / 2 + base, textPaint);
        canvas.drawText(right, getWidth() - getWidth() / 4, getHeight() / 2 + base, textPaint);
    }



}
