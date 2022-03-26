package com.mao.jetpack.ui.coroutine.repo

import com.mao.jetpack.netinterface.ImageService
import okhttp3.ResponseBody

/**
 *
 * @author zhangkun
 * @time 2021/8/20 11:16
 * @Description
 */
fun ImageRepository(service: ImageService = ImageService.create()): ImageRepository =
    ImageDataRepository(service)

interface ImageRepository {

    suspend fun downloadPicFromNet(fileUrl: String): ResponseBody
}
