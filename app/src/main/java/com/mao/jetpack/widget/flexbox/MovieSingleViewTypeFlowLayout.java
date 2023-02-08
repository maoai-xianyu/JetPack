package com.mao.jetpack.widget.flexbox;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.LayoutRes;
import androidx.collection.SparseArrayCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Wei Xiaomin on 2017/10/25.
 * 单一 ViewType 的 FlowLayout
 */

public class MovieSingleViewTypeFlowLayout extends MovieFlowLayout {

    protected Adapter mAdapter;
    protected DataChangeObserver mObserver;
    protected ArrayList<ViewHolder> mHolders;

    public MovieSingleViewTypeFlowLayout(Context context) {
        super(context);
    }

    public MovieSingleViewTypeFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieSingleViewTypeFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void drawLayout() {
        if (mAdapter == null || mAdapter.getCount() == 0) {
            removeAllViews();
            if (mHolders != null) {
                mHolders.clear();
            }
            return;
        }
        //wxm: 复用ItemView
        for (int i = 0; i < mAdapter.getCount(); i++) {
            ViewHolder holder;
            if (i < mHolders.size()) {
                holder = mHolders.get(i);
            } else {
                holder = mAdapter.onCreateViewHolder(this);
                mHolders.add(holder);
                addView(holder.itemView);
            }
            mAdapter.onBindViewHolder(holder, i);
        }
        //wxm: 移除多余的ItemView（可以用WeakHashMap优化？）
        for (int i = mHolders.size() - 1;
             mAdapter.getCount() < mHolders.size();
             i = mHolders.size() - 1) {

            mHolders.remove(i);
            removeViewAt(i);
        }
    }

    public void setAdapter(Adapter adapter) {
        if (mAdapter != null && mObserver != null) {
            mAdapter.unregisterDataSetObserver(mObserver);
        }
        mAdapter = adapter;
        mHolders = new ArrayList<>();

        if (mAdapter != null) {
            if (mObserver == null) {
                mObserver = new DataChangeObserver();
            }
            mAdapter.registerDataSetObserver(mObserver);
        }
        drawLayout();
    }

    public Adapter getAdapter() {
        return mAdapter;
    }

    class DataChangeObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            drawLayout();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }

    public abstract static class Adapter<T> extends BaseAdapter {

        protected List<T> data;
        private final int layoutId;

        public Adapter(List<T> data, @LayoutRes int layoutId) {
            this.data = data;
            this.layoutId = layoutId;
        }

        @Override
        public int getCount() {
            return this.data != null ? this.data.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return this.data != null ? this.data.get(position) : 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return convertView;
        }

        ViewHolder onCreateViewHolder(ViewGroup parent) {
            View itemVeiw = LayoutInflater.from(parent.getContext()).inflate(this.layoutId, parent, false);
            ViewHolder holder = new ViewHolder(itemVeiw);
            onViewHolderCreate(holder);
            return holder;
        }

        @SuppressWarnings("all")
        protected void onViewHolderCreate(ViewHolder holder) {
        }

        public abstract void onBindViewHolder(ViewHolder holder, int position);
    }

    public static final class ViewHolder {

        private View itemView;
        private final SparseArrayCompat<View> mArray;

        ViewHolder(View itemView) {
            this.itemView = itemView;
            mArray = new SparseArrayCompat<>(4);
        }

        @SuppressWarnings("unchecked")
        public <T extends View> T findView(int viewId) {
            View view = mArray.get(viewId);
            if (view != null) {
                return (T) view;
            }
            view = itemView.findViewById(viewId);
            if (view == null) {
                throw new NoSuchElementException("no this viewId");
            }
            mArray.put(viewId, view);
            return (T) view;
        }

        public View getItemView() {
            return itemView;
        }
    }
}
