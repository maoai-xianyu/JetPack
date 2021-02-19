package com.mao.jetpack.ui.room

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

/**
 *
 * @author zhangkun
 * @time 2021/2/19 9:29 PM
 * @Description
 */
@Database(entities = [Student::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): StudentDao
}