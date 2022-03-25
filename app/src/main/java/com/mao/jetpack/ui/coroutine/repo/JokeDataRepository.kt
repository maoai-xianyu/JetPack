package com.mao.jetpack.ui.coroutine.repo

import com.mao.jetpack.model.JokeListDataModel
import com.mao.jetpack.model.JokeOneDataModel
import com.mao.jetpack.netinterface.JokeService
import retrofit2.Call

/**
 *
 * @author zhangkun
 * @time 2021/8/20 11:18
 * @Description
 */
class JokeDataRepository(private val service: JokeService) : JokeRepository {

    override suspend fun queryJoke(key: String): JokeOneDataModel {
        return service.queryJoke(key)
    }

    override suspend fun queryJokeList(
        key: String,
        page: Int,
        pageSize: Int
    ): JokeListDataModel {
        return service.queryJokeList(key, page, pageSize)
    }
}
