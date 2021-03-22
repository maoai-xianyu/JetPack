package com.mao.jetpack.ui.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author zhangkun
 * @time 2021/3/11 2:20 PM
 * @Description 测试 普通类
 */
@Entity(tableName = "addressNew")
class AddressTest {
    @PrimaryKey(autoGenerate = true)
    var addressId = 0

    @ColumnInfo(name = "addressName")
    var name: String? = null

    constructor(name: String?) {
        this.name = name
    }

    constructor() {}
}