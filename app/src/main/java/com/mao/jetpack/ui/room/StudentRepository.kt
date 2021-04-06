package com.mao.jetpack.ui.room

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.mao.jetpack.ui.room.AppDataBase.Companion.appDataBase

/**
 *
 * @author zhangkun
 * @time 2021/3/24 6:02 PM
 * @Description
 */
class StudentRepository(var context: Context) {

    private var liveDataAllStudent: LiveData<List<Student>>? = null
    private val studentDao: StudentDao

    init {
        val appDataBase = appDataBase
        studentDao = appDataBase.userDao()
        if (liveDataAllStudent == null) {
            liveDataAllStudent = studentDao.getAllLiveDataStudent()
        }
    }

    fun insert(vararg students: Student) {
        studentDao.insert(*students)
    }

    fun delete(student: Student) {
        studentDao.delete(student)
    }

    fun update(student: Student) {
        studentDao.update(student)
    }

    fun getAll(): List<Student> {
        return studentDao.getAll()
    }

    fun getLiveDataAllStudent(): LiveData<List<Student>> {
        return studentDao.getAllLiveDataStudent()
    }

}