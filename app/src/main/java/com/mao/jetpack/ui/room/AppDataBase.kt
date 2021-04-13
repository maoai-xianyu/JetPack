package com.mao.jetpack.ui.room

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mao.jetpack.global.AppGlobalsKt

/**
 *
 * @author zhangkun
 * @time 2021/2/19 9:29 PM
 * @Description
 */
@Database(entities = [Student::class, Address::class], version = 2)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): StudentDao


    // 写法一
    companion object {
        val appDataBase = Single.appDataBase
    }

    object Single {

        // 数据的升级，当数据库升级的时候 需要修改 version 的版本号 version = 1  ->  version = 2
        private val migration1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table student add column flag integer not null default 1")
            }
        }


        // version = 2  -> version = 3
        private val migration2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

                // 原表的基础上删除一个字段
                //database.execSQL("create table student_temp (id integer primary key not null,name text,pwd text,addressId)")
                //database.execSQL("insert into student (id,name,pwd,addressid)" + " select id,name,pwd,addressId from student")
                //database.execSQL("drop table student")
                //database.execSQL("alter table student_temp rename to student")

            }
        }

        val appDataBase: AppDataBase =
            Room.databaseBuilder(AppGlobalsKt.get()!!, AppDataBase::class.java, "zkDB")
                .allowMainThreadQueries()
                //.fallbackToDestructiveMigration()
                .addMigrations(migration1_2)
                .build()

    }


    // 写法二

    /*companion object {

        private var database: AppDataBase? = null

        fun get(): AppDataBase {
            if (database == null) {


                //内存数据库,也就是说这种数据库当中存储的数据，只会存留在内存当中，进程被杀死之后，数据随之丢失
//                database = Room.inMemoryDatabaseBuilder(
//                    AppGlobalsKt.get()!!,
//                    AppDataBase::class.java
//                ).build()

                database = Room.databaseBuilder(
                    AppGlobalsKt.get()!!,
                    AppDataBase::class.java, "zdDB"
                )
                    // 允许在主线程操作数据库,默认是不允许，如果在主线程操作了数据库 会直接报错
                    //.allowMainThreadQueries()
                    // 数据库创建和打开后的回调
                    //.addCallback(callback)
                    //设置查询的线程池 默认是io线程池
                    //.setQueryExecutor()
                    // 工厂类 它是用来创建supportsqliteopenhelper ,frameworksqliteopenhelperFactory.
                    // 可以利用它自行创建supportsqliteopenhelper。来实现数据库的加密
                    //.openHelperFactory()
                    //room的日志模式
                    //.setJournalMode()
                    //数据库升级异常之后的回滚
                    //.fallbackToDestructiveMigration()
                    //数据库升级异常后根据指定版本进行回滚
                    //.fallbackToDestructiveMigrationFrom()
                    // 数据库升级的时候是必须要配置的
                    //.addMigrations(migration1_2)
                    .build()
            }

            return database!!
        }

        val callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }
        }

        // 升级的时候需要改 version 的版本
        val migration1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("alter table student add column cache_time LONG")
            }
        }


    }*/

}