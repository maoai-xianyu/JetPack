package com.mao.jetpack.image

import android.graphics.Bitmap

/**
 *
 * @author zhangkun
 * @time 2022/3/25 12:15
 * @Description
 */
class ImagePiece() {

    var index: Int = 0;
    var bitmap: Bitmap? = null

    constructor(index: Int, bitmap: Bitmap) : this()
}