package com.mao.jetpack.ui.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.mao.jetpack.databinding.ActivityRoomBinding
import com.mao.jetpack.utils.Logger
import kotlin.concurrent.thread

/**
 *
 * @author zhangkun
 * @time 2021/3/10 9:59 AM
 * @Description
 */
class RoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = ActivityRoomBinding.inflate(layoutInflater)
        root.text.text = "room ViewBinding"
        thread {
            val appDataBase: AppDataBase = Room.databaseBuilder(
                applicationContext,
                AppDataBase::class.java,
                "zkDB"
            ).allowMainThreadQueries()
                .build()

/*
            val appDataBase = AppDataBase.appDataBase
*/

            val userDao = appDataBase.userDao()

            userDao.insert(Student(1, "zk_1", "1111", 1))
            userDao.insert(Student(2, "zk_2", "2222", 2))
            userDao.insert(Student(3, "zk_3", "3333", 3))
            userDao.insert(Student(4, "zk_4", "4444", 4))
            val all = userDao.getAll()


            all.forEach {
                Logger.debug("it $it")
            }

        }

    }
}