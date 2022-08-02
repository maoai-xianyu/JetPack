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


    private void calculateText(CharSequence text) {
        Logger.error(" getMeasuredWidth() " + getMeasuredWidth());
        try {
            if (TextUtils.isEmpty(text)) {
                super.setText(text);
                return;
            }

            if (TextUtils.isEmpty(myDelimiter)) {
                Logger.error("分隔符不能为空~");
                super.setText(text);
                return;
            }

            if (myMaxLine <= 0) {
                Logger.error("maxLine = " + myMaxLine + " 最大行数不能小于等于0~");
                super.setText(text);
                return;
            }

            if (myReplaceSymbol == null) {
                Logger.error("myReplaceSymbol = " + myReplaceSymbol  + " 为空 不执行");
                super.setText(text);
                return;
            }

            // 获取可绘制的最大宽度
            int maxWidth = getMeasuredWidth() - getPaddingStart() - getPaddingEnd();
            //maxWidth = -17;

            Logger.error("maxWidth " + maxWidth);


            if (maxWidth < 0) {
                super.setText(text);
                Logger.error("maxWidth = " + maxWidth);
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

            Logger.error(" lastIndexOf  == "+ lastIndexOf);

            int cropIndex;
            if (lastIndexOf < 0) {
                cropIndex = context.length();
            } else {
                cropIndex = lastIndexOf;
            }

            Logger.error(" cropIndex  == " + cropIndex + " c length  == " + context.length());

            if (cropIndex <= 0) {
                super.setText(text);
                // 说明是从头开始进行的分隔，用户使用错误
                Logger.error("使用 " + myDelimiter + "从头进行分隔，用户使用错误~");
                return;
            }


            String tail;
            if (lastIndexOf < 0) {
                tail = myReplaceSymbol;
            } else {
                tail = myReplaceSymbol + context.substring(lastIndexOf);
            }

            Logger.debug(" tail  -- " + tail + "tail length == " + tail.length());

            StaticLayout lastLayout = createStaticLayout(tail, maxWidth);

            Logger.error("lastLayout.getLineCount()   == " + lastLayout.getLineCount() + " tail " + tail + "cropIndex " + cropIndex + " tail.length() " + tail.length());
            if (lastLayout.getLineCount() > myMaxLine) {
                super.setText(text);
                // 说明加上 myReplaceSymbol 已经 大于 最大行数
                Logger.error("使用 " + myDelimiter + " 分隔后，后面部分已经大于设置行数，用户使用错误~");
                return;
            }

            while (true) {
                Logger.error("cropIndex " + cropIndex);
                if (cropIndex < 0) {
                    super.setText(text);
                    break;
                }
                StaticLayout tempLayout = createStaticLayout(context.substring(0, cropIndex) + tail,
                        maxWidth);
                Logger.debug("tempLayout " + tempLayout.getLineCount() + " text " + tempLayout.getText());
                if (tempLayout.getLineCount() <= myMaxLine) {
                    super.setText(tempLayout.getText());
                    break;
                } else {
                    cropIndex -= 1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 需要在控件的布局添加 textview 自有属性的 maxlines 和 ellipsize
            super.setText(text);
        }
    }

    /**
     * 在当前宽度下测量显示的字，可以获取行数
     * 插入一个不占空间的字符 "\u200B"
     * 决定了只能用一个字符进行分隔
     *
     * @param text
     * @param maxWidth
     * @return
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
     * @param context       不能为空
     * @param delimiter     不能为空
     * @param maxLine       不能小于0
     * @param replaceSymbol 不能为空
     */
    public void setTextEllipsize(@NonNull String context, @NonNull String delimiter, int maxLine,
                                 @NonNull String replaceSymbol) {
        this.myDelimiter = delimiter;
        this.myMaxLine = maxLine;
        this.myReplaceSymbol = replaceSymbol;
        super.setText(context);
        post(new Runnable() {
            @Override
            public void run() {
                calculateText(context);
            }
        });
    }

}
