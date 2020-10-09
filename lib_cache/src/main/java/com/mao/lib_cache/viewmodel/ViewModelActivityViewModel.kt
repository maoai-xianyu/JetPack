package com.mao.lib_cache.viewmodel

import com.mao.lib_cache.model.UserKotlin

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