package com.mao.jetpack.image

import android.graphics.Bitmap

/**
 *
 * @author zhangkun
 * @time 2022/3/25 12:16
 * @Description
 */
object ImageSplitter {

    fun split(bitmap: Bitmap, xPiece: Int, yPiece: Int): List<ImagePiece> {
        val pieces: MutableList<ImagePiece> = ArrayList(xPiece * yPiece)
        val width = bitmap.width
        val height = bitmap.height
        val pieceWidth = width / 3
        val pieceHeight = height / 3
        for (i in 0 until yPiece) {
            for (j in 0 until xPiece) {
                val piece = ImagePiece()
                piece.index = j + i * xPiece
                val xValue = j * pieceWidth
                val yValue = i * pieceHeight
                piece.bitmap = Bitmap.createBitmap(
                    bitmap, xValue, yValue,
                    pieceWidth, pieceHeight
                )
                pieces.add(piece)
            }
        }
        return pieces
    }
}