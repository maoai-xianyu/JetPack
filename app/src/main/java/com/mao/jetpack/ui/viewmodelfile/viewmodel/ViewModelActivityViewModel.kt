package com.mao.jetpack.ui.viewmodelfile.viewmodel

import com.mao.jetpack.ui.viewmodelfile.model.UserKotlin

/**
 *
 * @author zhangkun
 * @time 2020/9/25 12:03 PM
 * @Description
 */

class ViewModelActivityViewModel {

    val userKotlin: UserKotlin = UserKotlin()

    fun getUser(): UserKotlin {
        userKotlin.name = "zhangnan"
        userKotlin.age = 12
        return userKotlin
    }
}