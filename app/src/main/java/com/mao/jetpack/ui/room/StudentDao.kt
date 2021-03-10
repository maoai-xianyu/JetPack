package com.mao.jetpack.ui.room

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
    fun getStudentByName(name: String): Student

    @Query("select * from student where id in (:useIds)")
    //fun getAllIds(vararg useIds:Int):MutableList<Student>
    fun getAllIds(useIds: IntArray ): MutableList<Student>
}