package com.mao.jetpack.ui.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

    private lateinit var rootBinding: ActivityRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootBinding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(rootBinding.root)
        rootBinding.text.text = "room ViewBinding"

        thread {
            /*val appDataBase: AppDataBase = Room.databaseBuilder(
                applicationContext,
                AppDataBase::class.java,
                "zkDB"
            ).build()*/

            val appDataBase = AppDataBase.appDataBase

            val userDao = appDataBase.userDao()

            Logger.error("------------------ 插入数据 和 查询所有")
            userDao.insert(Student("zk_1", "1111", 1))
            userDao.insert(Student("zk_2", "2222", 2))
            userDao.insert(Student("zk_3", "3333", 3))
            userDao.insert(Student("zk_4", "4444", 4))
            val all = userDao.getAll()


            all.forEach {
                Logger.debug("it $it")
                Logger.debug("it ${it.id}")
            }


            val add = AddressTest()

            Logger.error("------------------ 根据名字查询同名学生")

            val student = userDao.getStudentByName("zk_4")

            student.forEach {
                Logger.debug("student 同名 $student ")
            }


            Logger.error("------------------ 根据 id 查询数据")
            val students = userDao.getAllIds(intArrayOf(1, 2, 3))

            students.forEach {
                Logger.debug("getAllIds $it")
            }


            Logger.error("------------------ 根据 name 和 pwd 在 student 表中")
            val studentsT = userDao.getStudentTBy()

            studentsT.forEach {
                Logger.debug("studentsT $it")
            }

        }

    }
}