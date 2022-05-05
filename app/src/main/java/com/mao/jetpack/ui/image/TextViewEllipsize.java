package com.mao.jetpack.ui.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.mao.jetpack.R;
import com.mao.jetpack.utils.Logger;

import org.jetbrains.annotations.NotNull;

/**
 * @author zhangkun
 * @time 2022/5/4 9:44 上午
 * @Description 因为在所有的字符的都插入了
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
        myDelimiter = typedArray.getString(R.styleable.TextViewEllipsize_my_delimiter);
        myMaxLine = typedArray.getInt(R.styleable.TextViewEllipsize_my_maxLine, 1);
        myReplaceSymbol = typedArray.getString(R.styleable.TextViewEllipsize_my_replace_symbol);
        typedArray.recycle();
    }

    /**
     * 布局文件中设置了对应属性
     *
     * @param text
     * @param type
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        post(new Runnable() {
            @Override
            public void run() {
                try {
                    calculateText(text, type);
                } catch (Exception e) {
                    Logger.error(e.getMessage());
                }
            }
        });
    }

    private void calculateText(CharSequence text, BufferType type) {
        Logger.error(" getMeasuredWidth() " + getMeasuredWidth());
        if (TextUtils.isEmpty(text)) {
            super.setText(text, type);
            return;
        }

        if (TextUtils.isEmpty(myDelimiter)) {
            throw new IllegalArgumentException("分隔符不能为空~");
        }

        if (myMaxLine <= 0) {
            throw new IllegalArgumentException("maxLine = " + myMaxLine + " 最大行数不能小于等于0~");
        }

        // 获取可绘制的最大宽度
        int maxWidth = getMeasuredWidth() - getPaddingStart() - getPaddingEnd();
        StaticLayout staticLayout = createStaticLayout(text, maxWidth, true);
        // 如果小于最大行数,则直接绘制即可
        if (staticLayout.getLineCount() <= myMaxLine) {
            super.setText(staticLayout.getText(), type);
            return;
        }

        String context = text.toString();
        int lastIndexOf = context.lastIndexOf(myDelimiter);

        int cropIndex;
        if (lastIndexOf < 0) {
            cropIndex = context.length();
        } else {
            cropIndex = lastIndexOf;
        }

        Logger.error(" cropIndex  -- " + cropIndex + " c length" + context.length());

        if (cropIndex <= 0) {
            // 说明是从头开始进行的分隔，用户使用错误
            throw new IllegalArgumentException("使用 " + myDelimiter + "从头进行分隔，用户使用错误~");
        }

        String tail;
        if (lastIndexOf < 0) {
            tail = myReplaceSymbol;
        } else {
            tail = myReplaceSymbol + context.substring(lastIndexOf);
        }

        StaticLayout lastLayout = createStaticLayout(tail, maxWidth, false);

        Logger.error("lastLayout.getLineCount()   --- " + lastLayout.getLineCount() + " tail " + tail);
        if (lastLayout.getLineCount() > myMaxLine) {
            // 说明加上 myReplaceSymbol 已经 大于 最大行数
            throw new IllegalArgumentException("使用 " + myDelimiter + " 分隔后，后面部分已经大于设置行数，用户使用错误~");
        }

        while (true) {
            StaticLayout tempLayout = createStaticLayout(context.substring(0, cropIndex) + tail, maxWidth, false);
            if (tempLayout.getLineCount() <= myMaxLine) {
                super.setText(tempLayout.getText(), type);
                break;
            } else {
                cropIndex -= 1;
            }
        }
    }

    /**
     * 在当前宽度下测量显示的字，可以获取行数
     * 插入一个不占空间的字符 "\u200B"
     * 决定了只能用一个字符进行分隔
     *
     * @param text
     * @param maxWidth
     * @param needInsert
     * @return
     */
    private StaticLayout createStaticLayout(CharSequence text, int maxWidth, boolean needInsert) {
        StringBuilder stringBuilder = new StringBuilder();
        if (needInsert) {
            for (int i = 0; i < text.length(); i++) {
                if (i != text.length()) {
                    // 无宽度字符，可以切割所有字符，可以把英文切割
                    String ZERO_WIDTH_SPACE = "\u200B";
                    stringBuilder.append(text.charAt(i)).append(ZERO_WIDTH_SPACE);
                }
            }
        } else {
            stringBuilder.append(text);
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

    /**
     * 如果布局文件未设置，可以调用方法设置
     *
     * @param context       不能为空
     * @param delimiter     不能为空
     * @param maxLine       不能小于0
     * @param replaceSymbol 不能为空
     */
    public void setTextEllipsize(@NotNull String context, @NotNull String delimiter, int maxLine,
                                 @NotNull String replaceSymbol) {
        this.myDelimiter = delimiter;
        this.myMaxLine = maxLine;
        this.myReplaceSymbol = replaceSymbol;
        super.setText(context);
    }

}
