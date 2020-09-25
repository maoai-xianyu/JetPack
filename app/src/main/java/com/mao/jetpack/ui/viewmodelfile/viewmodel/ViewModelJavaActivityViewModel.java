package com.mao.jetpack.ui.viewmodelfile.viewmodel;

import androidx.lifecycle.ViewModel;

import com.mao.jetpack.ui.viewmodelfile.model.User;

/**
 * @author zhangkun
 * @time 2020/9/24 9:31 PM
 * @Description
 */
public class ViewModelJavaActivityViewModel extends ViewModel {

    public User user = new User();

    // 数据是从服务器获取到的
    public User getUser() {
        // 发一个请求给服务器，得到一个user对象
        user.setAge(12);
        user.setName("zhang");
        return user;
    }

}
