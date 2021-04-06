package com.mao.jetpack.ui.room;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * @author zhangkun
 * @time 2021/3/24 7:55 PM
 * @Description
 */
public class StudentRepositoryJava {

    private LiveData<List<Student>> listLiveData;

    private StudentDao studentDao;

    public StudentRepositoryJava(Context context) {
        AppDataBase appDataBase = AppDataBase.Companion.getAppDataBase();
        studentDao = appDataBase.userDao();
        if (listLiveData == null) {
            listLiveData = studentDao.getAllLiveDataStudent();
        }
    }

    void insert(Student... students) {
        studentDao.insert(students);
    }


    void delete(Student student) {
        studentDao.delete(student);
    }

    void update(Student student) {
        studentDao.update(student);
    }

    List<Student> getAll() {
        return studentDao.getAll();
    }

    LiveData<List<Student>> getListLiveData() {
        return studentDao.getAllLiveDataStudent();
    }

}
