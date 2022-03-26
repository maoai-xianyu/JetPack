package com.mao.jetpack.ui.coroutine.repo

import com.mao.jetpack.netinterface.ImageService
import okhttp3.ResponseBody

/**
 *
 * @author zhangkun
 * @time 2021/8/20 11:18
 * @Description
 */
class ImageDataRepository(private val service: ImageService) : ImageRepository {

    override suspend fun downloadPicFromNet(fileUrl: String): ResponseBody {
        return service.downloadPicFromNet(fileUrl)
    }
}
