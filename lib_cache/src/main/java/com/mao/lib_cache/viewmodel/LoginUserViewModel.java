package com.mao.lib_cache.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mao.lib_cache.dao.UserLoginDao;
import com.mao.lib_cache.database.CacheDataBase;
import com.mao.lib_cache.model.UserLogin;


/**
 * @author zhangkun
 * @time 2020/9/24 9:31 PM
 * @Description
 */
public class LoginUserViewModel extends ViewModel {

    public MutableLiveData<UserLogin> loginMutableLiveData;


    // getUser 对应布局文件中的 @{viewModel.user.name} 中的 user  如果 getUser 名字改了就 user 也需要改
    // 当然 userLiveDataMutableLiveData 可以替换 user @{viewModel.userLiveDataMutableLiveData.name}
    public MutableLiveData<UserLogin> getUser() {
        // 发送一个请求, 获取  UserLiveData 对象
        if (loginMutableLiveData == null) {
            loginMutableLiveData = new MutableLiveData<>();
            UserLogin user = new UserLogin();
            user.setAccount("zhangsan");
            user.setPassword("1111111");
            loginMutableLiveData.postValue(user);
        }
        return loginMutableLiveData;
    }


    public void loginRequest() {
        // 首先 从xml中获取到账号和密码等信息，然后去访问服务器
        UserLogin userLogin = loginMutableLiveData.getValue();
        // ...
        // 把从服务器获取的数据，存在数据库中
        CacheDataBase databaseName = CacheDataBase.getDatabase();
        // 声明要操作的表是哪张表
        UserLoginDao userLoginDao = databaseName.userLoginDao();
        userLoginDao.insertAll(userLogin);

        // 访问服务器成功后，服务器返回数据
        loginMutableLiveData.postValue(userLogin);
    }


}
