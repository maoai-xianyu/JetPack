package com.mao.jetpack.model

data class JokeOneDataModel(
    val error_code: Int,
    val reason: String,
    val result: List<ResultModel>?
)

data class ResultModel(
    val content: String,
    val hashId: String,
    val unixtime: Int
)
