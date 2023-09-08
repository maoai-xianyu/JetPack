package com.mao.jetpack.ui;

import android.content.Context;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author zhangkun
 * @time 2022/5/4 9:44 上午
 * @Description 因为在所有的字符的都插入了不占空间的 "\u200B"，只能用一个字符进行分隔
 */
public class TextViewEllipsizeMY extends AppCompatTextView {

    private String myDelimiter;
    private int myMaxLine; // 默认是 2
    private String myReplaceSymbol;

    public TextViewEllipsizeMY(@NonNull Context context) {
        this(context, null);
    }

    public TextViewEllipsizeMY(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewEllipsizeMY(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void calculateText(CharSequence text) {
        try {
            if (TextUtils.isEmpty(text)) {
                super.setText(text);
                return;
            }

            if (TextUtils.isEmpty(myDelimiter)) {
                //Log.e("----", "分隔符不能为空~");
                super.setText(text);
                return;
            }

            if (myMaxLine <= 0) {
                //Log.e("----", "maxLine = " + myMaxLine + " 最大行数不能小于等于0~");
                super.setText(text);
                return;
            }

            if (myReplaceSymbol == null) {
                //Log.e("----", "myReplaceSymbol = " + myReplaceSymbol + " 为空不执行");
                super.setText(text);
                return;
            }

            // 获取可绘制的最大宽度
            int maxWidth = getMeasuredWidth() - getPaddingStart() - getPaddingEnd();
            // maxWidth 不能为负数，防止 StaticLayout 传入负数导致奔溃
            if (maxWidth < 0) {
                //Log.e("----", "maxWidth " + maxWidth + "为负数");
                super.setText(text);
                return;
            }

            StaticLayout staticLayout = createStaticLayout(text, maxWidth);
            // 如果小于最大行数,则直接绘制即可
            if (staticLayout.getLineCount() <= myMaxLine) {
                super.setText(staticLayout.getText());
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

            if (cropIndex <= 0) {
                // 说明是从头开始进行的分隔，用户使用错误
                //Log.e("----", "使用 " + myDelimiter + "从头进行分隔，用户使用错误~");
                super.setText(text);
                return;
            }

            String tail;
            if (lastIndexOf < 0) {
                tail = myReplaceSymbol;
            } else {
                tail = myReplaceSymbol + context.substring(lastIndexOf);
            }

            StaticLayout lastLayout = createStaticLayout(tail, maxWidth);

            if (lastLayout.getLineCount() > myMaxLine) {
                // 说明加上 myReplaceSymbol 已经 大于 最大行数
                //Log.e("----", "使用 " + myDelimiter + " 分隔后，后面部分已经大于设置行数，用户使用错误~");
                super.setText(text);
                return;
            }

            while (true) {
                if (cropIndex < 0) {
                    super.setText(text);
                    break;
                }
                StaticLayout tempLayout = createStaticLayout(context.substring(0, cropIndex) + tail, maxWidth);
                if (tempLayout.getLineCount() <= myMaxLine) {
                    super.setText(tempLayout.getText());
                    break;
                } else {
                    cropIndex -= 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 需要在控件的布局添加 textview 自有属性的 maxLines 和 ellipsize  用于兜底
            super.setText(text);
        }
    }

    /**
     * 在当前宽度下测量显示的字，可以获取行数
     * 插入一个不占空间的字符 "\u200B"
     * 决定了只能用一个字符进行分隔
     */
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

    /**
     * 如果布局文件未设置，可以调用方法设置
     *
     * @param context 不能为空
     * @param delimiter 不能为空
     * @param maxLine 不能小于0
     * @param replaceSymbol 不能为空
     */
    public void setTextEllipsize(@NonNull final String context, @NonNull String delimiter, int maxLine,
        @NonNull String replaceSymbol) {
        this.myDelimiter = delimiter;
        this.myMaxLine = maxLine;
        this.myReplaceSymbol = replaceSymbol;
        post(new Runnable() {
            @Override
            public void run() {
                calculateText(context);
            }
        });
    }
}