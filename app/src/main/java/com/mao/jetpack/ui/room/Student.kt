package com.mao.jetpack.ui.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author zhangkun
 * @time 2021/2/19 9:23 PM
 * @Description
 */
@Entity(tableName = "student")
data class Student(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "pwd")
    val password: String?,
    @ColumnInfo(name = "addressId")
    val addressId: Int

)