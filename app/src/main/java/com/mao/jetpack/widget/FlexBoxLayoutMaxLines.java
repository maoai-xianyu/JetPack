package com.mao.jetpack.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.view.ViewCompat;

import com.google.android.flexbox.FlexLine;
import com.google.android.flexbox.FlexboxLayout;

import java.util.List;

public class FlexBoxLayoutMaxLines extends FlexboxLayout {
    private int maxLines = NOT_SET;

    private boolean isTrim = false;

    private int lines = NOT_SET;

    private OnLinesChangedListener onLinesChangedListener;

    public int getLines() {
        return lines;
    }

    public void setOnLinesChangedListener(OnLinesChangedListener onLinesChangedListener) {
        this.onLinesChangedListener = onLinesChangedListener;
    }

    @Override
    public void setMaxLine(int maxLine) {
        maxLines = maxLine;
        isTrim = false;
        if (ViewCompat.isAttachedToWindow(this)) {
            requestLayout();
            invalidate();
        }
    }

    public int getMaxLines() {
        return maxLines;
    }

    /**
     * see {@link #getMaxLines()}
     */
    @Deprecated
    @Override
    public int getMaxLine() {
        return NOT_SET;
    }

    public FlexBoxLayoutMaxLines(Context context) {
        this(context, null);
    }

    public FlexBoxLayoutMaxLines(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlexBoxLayoutMaxLines(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setMaxLine(super.getMaxLine());
        super.setMaxLine(NOT_SET);
    }

    @Override
    public List<FlexLine> getFlexLinesInternal() {
        List<FlexLine> flexLines = super.getFlexLinesInternal();
        lines = flexLines.size();
        if (onLinesChangedListener != null && !isTrim) {
            onLinesChangedListener.onLinesChanged(lines);
        }
        if (maxLines > 0 && lines > maxLines) {
            isTrim = true;
            flexLines.subList(maxLines, lines).clear();
        }
        return flexLines;
    }

    public interface OnLinesChangedListener {
        void onLinesChanged(int lines);
    }
}
