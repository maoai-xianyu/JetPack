package com.mao.jetpack.ui.room

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 * @author zhangkun
 * @time 2021/3/24 9:16 PM
 * @Description
 */
class RoomViewModel(application: Application) : AndroidViewModel(application) {

    private val studentRepository: StudentRepository = StudentRepository(application)


    fun insert(vararg students: Student) {
        studentRepository.insert(*students)
    }

    fun delete(student: Student) {
        studentRepository.delete(student)
    }

    fun update(student: Student) {
        studentRepository.update(student)
    }

    fun getAll(): MutableList<Student> {
        return studentRepository.getAll() as MutableList<Student>
    }

    fun getLiveDataAllStudent(): LiveData<List<Student>> {
        return studentRepository.getLiveDataAllStudent()
    }


}