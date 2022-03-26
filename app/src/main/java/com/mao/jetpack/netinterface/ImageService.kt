package com.mao.jetpack.netinterface

import com.mao.base.net.RetrofitFactory
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 *
 * @author zhangkun
 * @time 2022/3/25 12:08
 * @Description
 */
interface ImageService {

    companion object {
        @JvmStatic
        fun create(
        ): ImageService = RetrofitFactory.instance.create(ImageService::class.java)
    }

    @GET
    suspend fun downloadPicFromNet(@Url fileUrl: String): ResponseBody
}