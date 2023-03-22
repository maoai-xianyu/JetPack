package com.mao.jetpack.widget.rebound;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.mao.jetpack.R;


/**
 * 不能用
 */
public class CustomLayoutManager extends RecyclerView.LayoutManager {
    private static final float SCALE_FACTOR = 0.6f;
    private int itemWidth;
    private int horizontalSpace;

    public CustomLayoutManager(Context context) {
        horizontalSpace = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_item_space);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }
        // 计算 RecyclerView 中每个子项的宽度
        itemWidth = (getWidth() - horizontalSpace * 2) / 3;
        detachAndScrapAttachedViews(recycler);
        int itemCount = getItemCount();
        int left = horizontalSpace;
        int right = left + itemWidth;
        for (int i = 0; i < itemCount; i++) {
            View view = recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view, 0, 0);
            int top = getHeight() / 2 - getDecoratedMeasuredHeight(view) / 2;
            int bottom = top + getDecoratedMeasuredHeight(view);
            layoutDecorated(view, left, top, right, bottom);
            left += itemWidth + horizontalSpace;
            right = left + itemWidth;
        }
        scaleViews();
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int delta = -dx;
        offsetChildrenHorizontal(delta);
        scaleViews();
        recycleViews(delta, recycler);
        return delta;
    }

    private void scaleViews() {
        int centerX = getWidth() / 2;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childCenterX = getDecoratedLeft(child) + itemWidth / 2;
            int distanceFromCenter = Math.abs(centerX - childCenterX);
            float scale = 1 - (SCALE_FACTOR * distanceFromCenter) / centerX;
            child.setScaleX(scale);
            child.setScaleY(scale);
        }
    }

    private void recycleViews(int dx, RecyclerView.Recycler recycler) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (dx > 0 && getDecoratedRight(child) < 0) {
                removeAndRecycleView(child, recycler);
            } else if (dx < 0 && getDecoratedLeft(child) > getWidth()) {
                removeAndRecycleView(child, recycler);
            }
        }
    }
}
