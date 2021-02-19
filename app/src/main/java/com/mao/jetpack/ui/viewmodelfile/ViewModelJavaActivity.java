package com.mao.jetpack.ui.viewmodelfile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mao.jetpack.R;
import com.mao.jetpack.databinding.ActivityViewModelJavaBinding;
import com.mao.jetpack.ui.viewmodelfile.viewmodel.ViewModelJavaActivityViewModel;
import com.mao.jetpack.utils.Logger;

/**
 * @author zhangkun
 * @time 2020/9/24 5:45 PM
 * @Description
 */
public class ViewModelJavaActivity extends AppCompatActivity {


    private TextView tvText;
    private Button tvButton;
    private ViewModelJavaActivityViewModel viewModelJavaActivityViewModel; // 数据源

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_view_model);
        //tvButton = findViewById(R.id.tvButton);
        //initListener();

        // 请求服务器  得到数据  然后更新到UI
        // 数据层 和视图层 和 P层(C层)都有耦合
        //tvText = findViewById(R.id.tvText);

        // 把解析xml文件交给DataBinding做  ActivityViewModelBinding 和 activity_view_model_java.xml 对应
        // ActivityViewModelJavaBinding 用来管理 activity_view_model_java.xml 和 ViewModelJavaActivityViewModel 的数据源
        ActivityViewModelJavaBinding viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_model_java);
        // 绑定当前窗体的生命周期
        viewDataBinding.setLifecycleOwner(this);

        viewModelJavaActivityViewModel = new ViewModelProvider(this).get(ViewModelJavaActivityViewModel.class);
        // 绑定数据源
        viewDataBinding.setViewModel(viewModelJavaActivityViewModel);
        // 添加点击事件
        viewDataBinding.setPresenter(new Presenter());
        // 请求服务器，得到User, xml 就更新了数据
        viewModelJavaActivityViewModel.getUser();

        viewModelJavaActivityViewModel.getCurrentName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                viewDataBinding.tvTextShow.setText(s);
            }
        });


    }

    public void send(View view) {
        Logger.debug(" 发送2 点击事件 " + viewModelJavaActivityViewModel.user.getName());
    }


    public static class Presenter {

        public void onClick(ViewModelJavaActivityViewModel viewModel, View view) {
            if (view.getId() == R.id.btnClick) {
                String currentName = "maoai_xianyu " + viewModel.i++;
                viewModel.getCurrentName().setValue(currentName);
            } else {
                Logger.debug("发送1 点击事件 " + viewModel.user.getName());
            }
        }
    }


    /**
     * 屏幕切换时保存状态，修改系统配置，修改系统的字体大小，或者屏幕旋转
     * @return
     */
    @Nullable
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        Logger.debug("数据状态的保存  修改系统属性，变更回调方法 onRetainCustomNonConfigurationInstance ");
        return super.onRetainCustomNonConfigurationInstance();
    }


    /**
     * 数据状态的获取 -> 打开时会调用，屏幕切换的时候会调用
     * @return
     */
    @Nullable
    @Override
    public Object getLastNonConfigurationInstance() {
        Logger.debug("数据状态的获取 修改系统属性，变更回调方法 getLastNonConfigurationInstance ");
        return super.getLastNonConfigurationInstance();
    }

}
