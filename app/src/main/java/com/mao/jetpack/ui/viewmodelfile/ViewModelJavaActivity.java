package com.mao.jetpack.ui.viewmodelfile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.mao.jetpack.R;
import com.mao.jetpack.databinding.ActivityViewModelJavaBinding;
import com.mao.lib_common.utils.Logger;
import com.mao.lib_cache.viewmodel.ViewModelJavaActivityViewModel;

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
        viewModelJavaActivityViewModel = new ViewModelJavaActivityViewModel();
        // 绑定数据源
        viewDataBinding.setViewModel(viewModelJavaActivityViewModel);
        // 添加点击事件
        viewDataBinding.setPresenter(new Presenter());
        // 请求服务器，得到User, xml 就更新了数据
        viewModelJavaActivityViewModel.getUser();

        // 准备引入DataBinding 和 ViewModel 联合使用
        // 把 User 对象绑定到 activity_view_model.xml 有两种方式：
        // 1. 直接把 User 绑定到 activity_view_model.xml
        // 2. 将 ViewModelJavaActivityViewModel 绑定到  activity_view_model.xml  一般的选择

    }

    public void send(View view) {
        Logger.debug(" 发送2 点击事件 " + viewModelJavaActivityViewModel.user.getName());
    }


    public static class Presenter {

        public void onClick(ViewModelJavaActivityViewModel viewModel) {
            Logger.debug("发送1 点击事件 " + viewModel.user.getName());
        }
    }
}
