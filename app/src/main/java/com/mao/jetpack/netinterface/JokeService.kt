package com.mao.jetpack.netinterface

import com.mao.base.net.RetrofitFactory
import com.mao.jetpack.model.JokeListDataModel
import com.mao.jetpack.model.JokeOneDataModel
import com.mao.jetpack.nethttp.HttpUrl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *
 * @author zhangkun
 * @time 2022/3/25 14:00
 * @Description
 */
interface JokeService {

    companion object {
        @JvmStatic
        fun create(
        ): JokeService = RetrofitFactory.instance.create(JokeService::class.java)
    }


    @GET(HttpUrl.JOKE_ONE_ACTION)
    suspend fun queryJoke(@Query("key") key: String): JokeOneDataModel


    @GET(HttpUrl.JOKE_LIST_ACTION)
    suspend fun queryJokeList(
        @Query("key") key: String,
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): JokeListDataModel
}