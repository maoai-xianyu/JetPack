package com.mao.jetpack.ui.viewmodelfile;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.mao.jetpack.R;
import com.mao.jetpack.databinding.ActivityViewModelJavaLiveDataBinding;
import com.mao.lib_common.utils.Logger;
import com.mao.lib_cache.model.UserLiveData;
import com.mao.lib_cache.viewmodel.ViewModelJavaLiveDataActivityViewModel;

/**
 * @author zhangkun
 * @time 2020/9/24 5:45 PM
 * @Description
 */
public class ViewModelJavaLiveDataActivity extends AppCompatActivity {


    private ViewModelJavaLiveDataActivityViewModel viewModelJavaActivityViewModel; // 数据源

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityViewModelJavaLiveDataBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_model_java_live_data);
        // 绑定当前窗体的生命周期
        viewDataBinding.setLifecycleOwner(this);
        viewModelJavaActivityViewModel = new ViewModelJavaLiveDataActivityViewModel();
        // 绑定数据源
        viewDataBinding.setViewModel(viewModelJavaActivityViewModel);
        // 添加点击事件
        viewDataBinding.setPresenter(new Presenter());

        // 跳转个人中心

        viewModelJavaActivityViewModel.getUser().observe(this, new Observer<UserLiveData>() {
            @Override
            public void onChanged(UserLiveData userLiveData) {
                if (userLiveData != null && userLiveData.getName().equals("zhang")){
                    Logger.debug("登录成功了");
                }

            }
        });

    }

    public void send(View view) {
        Logger.debug(" 发送2 点击事件 " + viewModelJavaActivityViewModel.getUser().getValue());
        viewModelJavaActivityViewModel.loginRequest();
    }


    public static class Presenter {

        public void onClick(ViewModelJavaLiveDataActivityViewModel viewModel) {
            Logger.debug("发送1 点击事件 "+viewModel.getUser().getValue());
            viewModel.loginRequest();
        }
    }
}
