package com.mao.jetpack.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.mao.jetpack.R;


/**
 *
 * 限制TextView字符长度，超出以“...”代替
 */

@SuppressLint("AppCompatCustomView")
public class MovieLimitLengthTextView extends TextView {

    protected int mLimitLength;
    protected CharSequence mRawText;

    public MovieLimitLengthTextView(Context context) {
        this(context, null);
    }

    public MovieLimitLengthTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieLimitLengthTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MovieLimitLengthTextView, defStyleAttr, 0);
        mLimitLength = a.getInteger(R.styleable.MovieLimitLengthTextView_limitLength, 0);
    }

    public void setLimitLength(int limitLength) {
        this.mLimitLength = limitLength;
    }

    public int getLimitLength() {
        return mLimitLength;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setText(CharSequence text, BufferType type) {
        this.mRawText = text;
        if (mLimitLength > 0 && text.length() > mLimitLength) {
            text = text.subSequence(0, mLimitLength - 1);
            super.setText(text + "...", type);
            return;
        }
        super.setText(text, type);
    }

    @Override
    public CharSequence getText() {
        return this.mRawText;
    }
}
