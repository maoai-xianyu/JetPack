package com.mao.jetpack.test

/**
 *
 * @author zhangkun
 * @time 2022/3/17 17:37
 * @Description
 *
 * out 协变 能取不能放
 */
class University1<out T>(val name: String) {
    fun get(): T? {
        return null
    }

    /*fun put(student: T) {

    }*/



    //University1<Student> 是 University1<FemaleStudent> 父类
}