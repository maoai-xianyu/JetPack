package com.mao.jetpack.ui.room

import androidx.room.*
import com.mao.jetpack.global.AppGlobals

/**
 *
 * @author zhangkun
 * @time 2021/2/19 9:29 PM
 * @Description
 */
@Database(entities = [Student::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): StudentDao

    companion object {
        val appDataBase = Single.appDataBase
    }

    object Single {
        val appDataBase: AppDataBase =
            Room.databaseBuilder(AppGlobals.getApplication(), AppDataBase::class.java, "zkDB")
                .addMigrations()
                .build()
    }
}