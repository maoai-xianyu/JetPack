package com.mao.jetpack.ui.viewmodelfile.viewmodel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mao.jetpack.ui.viewmodelfile.model.User
import com.mao.jetpack.ui.viewmodelfile.model.UserKotlin
import com.mao.jetpack.ui.viewmodelfile.model.UserLiveData

/**
 *
 * @author zhangkun
 * @time 2020/9/25 12:03 PM
 * @Description
 */

/*
class ViewModelActivityViewModel */
/*: ViewModel()*//*
 {

    var liveData: MutableLiveData<UserKotlin>? = null

    val user: MutableLiveData<UserKotlin>
        get() {
            // 发送一个请求, 获取  UserLiveData 对象
            if (liveData == null) {
                liveData = MutableLiveData()
                val user = UserKotlin()
                user.name = "zhangsan"
                user.age = 12
                liveData!!.postValue(user)
            }
            return liveData!!
        }


    val userKotlin = UserKotlin()


    val listData = MutableLiveData<UserKotlin>()

    val user: MutableLiveData<UserKotlin>
        get() {
            // 发送一个请求, 获取  UserLiveData 对象
            val user = UserKotlin()
            user.name = "zhangsan"
            user.age = 12
            listData.postValue(user)
            return listData
        }


    fun getUser(): MutableLiveData<UserKotlin> {
        return listData
    }
}
*/


// 这种方式，达不到双向绑定的效果
class ViewModelActivityViewModel :ViewModel(){
    val name = MutableLiveData<String>()
    val age = ObservableInt()
    val user = User()

    var userKotlin = UserKotlin()
        set(value) {
            value.name = "内部"
            value.age = 1111
            field = value
        }



    /*val user = UserKotlin()*/
    fun getUser(): UserKotlin {
        val userKotlin = UserKotlin()
        userKotlin.name = "测试"
        return userKotlin
    }
}