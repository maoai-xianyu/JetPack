package com.mao.jetpack.test

/**
 *
 * @author zhangkun
 * @time 2022/3/17 17:37
 * @Description
 *
 * in 逆变  能放不能取
 */
class University2<in T>(val name: String) {


    /*fun get(): T? {
        return null
    }*/


    fun put(student: T) {

    }



    // University2<FemaleStudent> 是  University2<Student>  的父类

}