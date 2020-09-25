package com.mao.jetpack.ui.viewmodelfile.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mao.jetpack.ui.viewmodelfile.model.UserLiveData;

/**
 * @author zhangkun
 * @time 2020/9/24 9:31 PM
 * @Description
 */
public class ViewModelJavaLiveDataActivityViewModel extends ViewModel {

    public MutableLiveData<UserLiveData> userLiveDataMutableLiveData;


    // getUser 对应布局文件中的 @{viewModel.user.name} 中的 user  如果 getUser 名字改了就 user 也需要改
    // 当然 userLiveDataMutableLiveData 可以替换 user @{viewModel.userLiveDataMutableLiveData.name}
    public MutableLiveData<UserLiveData> getUser() {
        // 发送一个请求, 获取  UserLiveData 对象
        if (userLiveDataMutableLiveData == null) {
            userLiveDataMutableLiveData = new MutableLiveData<>();
            UserLiveData user = new UserLiveData();
            user.setName("zhangsan");
            user.setAge(12);
            userLiveDataMutableLiveData.postValue(user);
        }
        return userLiveDataMutableLiveData;
    }


    public void loginRequest() {
        // 首先 从xml中获取到账号和密码等信息，然后去访问服务器
        UserLiveData userLiveData = userLiveDataMutableLiveData.getValue();

        // 访问服务器成功后，服务器返回数据
        userLiveDataMutableLiveData.postValue(userLiveData);
    }


}
