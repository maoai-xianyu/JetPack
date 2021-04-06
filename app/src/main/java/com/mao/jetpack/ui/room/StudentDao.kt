package com.mao.jetpack.ui.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

/**
 *
 * @author zhangkun
 * @time 2021/2/19 9:32 PM
 * @Description
 */
@Dao
interface StudentDao {

    @Insert
    fun insert(vararg students: Student)

    @Delete
    fun delete(student: Student)

    @Update
    fun update(student: Student)

    @Query("select * from student")
    fun getAll(): List<Student>


    @Query("select * from student where name like :name")
    fun getStudentByName(name: String): MutableList<Student>

    @Query("select * from student where id in (:useIds)")
    fun getAllIds(useIds: IntArray): MutableList<Student>
    //fun getAllIds(useIds: IntArray): MutableList<Student>

    // 只查询两个
    @Query("select name,pwd from student")
    fun getStudentTBy(): MutableList<StudentTuple>

    // 关联主外键 另外一张标品
    /*@Query("select name,pwd,addressName from student where student.addressId == address.addressId ")
    fun getS()*/


    // 使用live data
    @Query("select * from student order by id")
    fun getAllLiveDataStudent(): LiveData<List<Student>>
}