package com.mao.jetpack.ui.room

import androidx.room.ColumnInfo

/**
 *
 * @author zhangkun
 * @time 2021/3/11 10:48 AM
 * @Description
 */
data class StudentTuple(
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "pwd")
    val password: String?
)