package com.mao.jetpack.ui.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author zhangkun
 * @time 2021/3/11 2:15 PM
 * @Description  测试 数据类
 */
@Entity(tableName = "address")
data class Address(
    @ColumnInfo(name = "addressName")
    var addressName: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "addressId")
    var addressId = 0
}