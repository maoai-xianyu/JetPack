package com.mao.jetpack.ui.coroutine.repo

import com.mao.jetpack.model.JokeListDataModel
import com.mao.jetpack.model.JokeOneDataModel
import com.mao.jetpack.netinterface.JokeService

/**
 *
 * @author zhangkun
 * @time 2021/8/20 11:16
 * @Description
 */
fun JokeRepository(service: JokeService = JokeService.create()): JokeRepository =
    JokeDataRepository(service)

interface JokeRepository {

    suspend fun queryJoke(key: String): JokeOneDataModel

    suspend fun queryJokeList(
        key: String,
        page: Int,
        pageSize: Int
    ): JokeListDataModel
}
