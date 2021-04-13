package com.mao.jetpack.ui.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 *
 * @author zhangkun
 * @time 2021/2/19 9:23 PM
 * @Description  当有自增长的情况，需要把对应的id，放在实体类中
 * 添加主外键 , 组合查询，一般我们只针对单表进行查询
 */
/*@Entity(
    tableName = "student", foreignKeys = [ForeignKey(
        entity = Address::class,
        parentColumns = ["addressId"],
        childColumns = ["addressId"]
    )]
)*/

@Entity(tableName = "student")
data class Student(
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "pwd")
    val password: String?,
    @ColumnInfo(name = "addressId")
    val addressId: Int,
    @ColumnInfo(name = "flag")
    val flag: Boolean = false
) {
    // 因为是自增长，所以会被赋值，id 申明为 var
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}