package com.mao.lib_common.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @author zhangkun
 * @time 2020/10/9 8:52 PM
 * @Description
 */
public abstract class BaseFragment extends Fragment {
    // 根 view
    public View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutRes(),container,false);
        initView();
        return view;
    }

    /**
     * 返回布局文件
     * @return
     */
    protected abstract int getLayoutRes();

    // 初始化UI
    protected abstract void initView();



}
