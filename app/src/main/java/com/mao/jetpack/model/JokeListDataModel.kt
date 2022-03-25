package com.mao.jetpack.model


/**
 * FileName: JokeListData
 * Founder: LiuGuiLin
 * Profile: 笑话列表
 */

data class JokeListDataModel(
    val error_code: Int,
    val reason: String,
    val result: Results?
)

data class Results(
    val `data`: List<DataModel>?
)

data class DataModel(
    val content: String,
    val hashId: String,
    val unixtime: Int,
    val updatetime: String
)