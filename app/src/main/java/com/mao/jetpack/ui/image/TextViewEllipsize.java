package com.mao.jetpack.ui.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.mao.jetpack.R;
import com.mao.jetpack.utils.Logger;

/**
 * @author zhangkun
 * @time 2022/5/4 9:44 上午
 * @Description
 */
public class TextViewEllipsize extends AppCompatTextView {

    private String myDelimiter;
    private int myMaxLine; // 默认是 2
    private String myReplaceSymbol;

    public TextViewEllipsize(@NonNull Context context) {
        this(context, null);
    }

    public TextViewEllipsize(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewEllipsize(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextViewEllipsize);
        if (typedArray == null) {
            return;
        }
        myMaxLine = typedArray.getInt(R.styleable.TextViewEllipsize_my_maxLine, 2);
        myDelimiter = typedArray.getString(R.styleable.TextViewEllipsize_my_delimiter);
        myReplaceSymbol = typedArray.getString(R.styleable.TextViewEllipsize_my_replace_symbol);
        typedArray.recycle();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        post(new Runnable() {
            @Override
            public void run() {
                calculateText(text, type);
            }
        });
    }

    private void calculateText(CharSequence text, BufferType type) {
        Logger.error(" getMeasuredWidth() " + getMeasuredWidth());
        // 获取可绘制的最大宽度
        int maxWidth = getMeasuredWidth() - getPaddingStart() - getPaddingEnd();
        StaticLayout staticLayout = createStaticLayout(text, maxWidth);
        // 如果小于最大行数,则直接绘制即可
        if (staticLayout.getLineCount() <= myMaxLine) {
            super.setText(staticLayout.getText(), type);
            return;
        }

        String context = text.toString();
        int lastIndexOf = context.lastIndexOf(myDelimiter);

        String tail;
        if (lastIndexOf < 0) {
            tail = myReplaceSymbol;
        } else {
            tail = myReplaceSymbol + context.substring(lastIndexOf);
        }

        Logger.error("tail   --- " + tail);

        int cropIndex;
        if (lastIndexOf < 0) {
            cropIndex = context.length();
        } else {
            cropIndex = lastIndexOf;
        }

        while (true) {
            StaticLayout tempLayout = createStaticLayout(context.substring(0, cropIndex) + tail, maxWidth);
            if (tempLayout.getLineCount() <= myMaxLine) {
                super.setText(tempLayout.getText(), type);
                break;
            } else {
                cropIndex -= 1;
            }
        }
    }


    private StaticLayout createStaticLayout(CharSequence text, int maxWidth) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (i != text.length()) {
                // 无宽度字符，可以切割所有字符，可以把英文切割
                String ZERO_WIDTH_SPACE = "\u200B";
                stringBuilder.append(text.charAt(i)).append(ZERO_WIDTH_SPACE);
            }
        }
        // 返回可以测量的 StaticLayout
        return new StaticLayout(
                stringBuilder,
                getPaint(),
                maxWidth,
                Layout.Alignment.ALIGN_NORMAL,
                1f,
                0f,
                true
        );

    }
}
