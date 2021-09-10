package com.mao.base

import java.util.*

/**
 *
 * @author zhangkun
 * @time 2021/9/10 16:10
 * @Description
 */

inline fun <reified T> loadServiceLoader(): ServiceLoader<T> {
    // 把T::class当成类型形参的类访问
    return ServiceLoader.load(T::class.java)
}

inline fun <reified T> loadService(): T {
    // 把T::class当成类型形参的类访问
    return loadServiceLoader<T>().iterator().next()
}