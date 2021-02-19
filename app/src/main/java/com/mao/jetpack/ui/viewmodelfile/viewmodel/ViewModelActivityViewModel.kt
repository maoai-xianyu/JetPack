package com.mao.jetpack.ui.viewmodelfile.viewmodel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mao.jetpack.ui.viewmodelfile.model.User
import com.mao.jetpack.ui.viewmodelfile.model.UserKotlin
import com.mao.jetpack.ui.viewmodelfile.model.UserKotlinNew
import com.mao.jetpack.ui.viewmodelfile.model.UserLiveData

/**
 *
 * @author zhangkun
 * @time 2020/9/25 12:03 PM
 * @Description
 */
class ViewModelActivityViewModel {
    //方案一：LiveData 和 DataBinding 双向绑定：需要 viewModelBinding.lifecycleOwner 需要绑定 viewModelBinding.lifecycleOwner
    val name = MutableLiveData<String>()

    //方案二：DataBinding 和 BaseObservable 双向绑定，不需要 viewModelBinding.lifecycleOwner， UserKotlin 实现 BaseObservable
    //val user = UserKotlin()
    init {
        name.value = "MutableLiveData<String> 测试"
        //user.name = "同在布局中设置 user 一样"
        //user.age = 111
    }

    //方案二：DataBinding 和 BaseObservable 双向绑定，不需要 viewModelBinding.lifecycleOwner，UserKotlin 实现 BaseObservable
    // UserKotlin 实现了 BaseObservable 可以双向绑定，这个其实和   val user = UserKotlin() 一样
    /*var liveData: MutableLiveData<UserKotlin>? = null
    val user: MutableLiveData<UserKotlin>
        get() {
            // 发送一个请求, 获取  UserLiveData 对象
            if (liveData == null) {
                liveData = MutableLiveData()
                val userKotlin = UserKotlin()
                userKotlin.name = "MutableLiveData<UserKotlin>()"
                userKotlin.age = 1111
                liveData!!.value = userKotlin
            }
            return liveData!!
        }*/

    val userKotlin = UserKotlin()


    // UserKotlinNew 没有实现 BaseObservable 不能双向绑定 写法有问题
    /*var liveData: MutableLiveData<UserKotlinNew>? = null
    val user: MutableLiveData<UserKotlinNew>
        get() {
            // 发送一个请求, 获取  UserLiveData 对象
            if (liveData == null) {
                liveData = MutableLiveData()
                val userKotlinNew = UserKotlinNew("MutableLiveData<UserKotlinNew>()", 123)
                liveData!!.value = userKotlinNew
            }
            return liveData!!
        }*/

    val user = MutableLiveData<UserKotlinNew>(UserKotlinNew("MutableLiveData<UserKotlinNew>()",123))

    fun changeUserName(str: String) {
        val value = user.value
        value?.name = str
        user.value = value
    }


}