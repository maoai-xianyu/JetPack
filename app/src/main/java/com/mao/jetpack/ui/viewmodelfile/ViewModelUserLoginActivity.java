package com.mao.jetpack.ui.viewmodelfile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.mao.jetpack.R;
import com.mao.jetpack.databinding.ActivityViewModelJavaLoginBinding;
import com.mao.jetpack.ui.navigation.NavigationIndexActivity;
import com.mao.lib_cache.model.UserLogin;
import com.mao.lib_cache.viewmodel.LoginUserViewModel;
import com.mao.lib_common.utils.Logger;

/**
 * @author zhangkun
 * @time 2020/9/24 5:45 PM
 * @Description
 */
public class ViewModelUserLoginActivity extends AppCompatActivity {


    private LoginUserViewModel viewModel; // 数据源

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityViewModelJavaLoginBinding viewDataBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_view_model_java_login);
        // 绑定当前窗体的生命周期
        viewDataBinding.setLifecycleOwner(this);
        viewModel = new LoginUserViewModel();
        // 绑定数据源
        viewDataBinding.setViewModel(viewModel);
        // 添加点击事件
        viewDataBinding.setPresenter(new Presenter());

        // 跳转个人中心

        viewModel.getUser().observe(this, new Observer<UserLogin>() {
            @Override
            public void onChanged(UserLogin userLogin) {
                if (userLogin != null && userLogin.getAccount().equals("zhangsan")) {
                    Logger.debug("登录成功了");
                    Intent intent = new Intent(ViewModelUserLoginActivity.this, NavigationIndexActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }


    public void sendLogin(View view) {
        Logger.debug(" 登录1 点击事件 " + viewModel.getUser().getValue());
        viewModel.loginRequest();
    }


    public static class Presenter {

        public void onClick(LoginUserViewModel viewModel) {
            Logger.debug("登录2 点击事件 " + viewModel.getUser().getValue());
            viewModel.loginRequest();
        }
    }
}
