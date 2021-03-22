package com.mao.jetpack.ui.room

import androidx.room.Dao
import androidx.room.Insert

/**
 *
 * @author zhangkun
 * @time 2021/3/19 10:23 AM
 * @Description
 */
@Dao
interface AddressDao {
    @Insert
    fun insert(address: Address)
}