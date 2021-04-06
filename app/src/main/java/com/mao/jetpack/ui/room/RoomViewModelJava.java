package com.mao.jetpack.ui.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * @author zhangkun
 * @time 2021/3/30 10:26 AM
 * @Description
 */
public class RoomViewModelJava extends AndroidViewModel {

    private StudentRepositoryJava studentRepositoryJava;

    public RoomViewModelJava(Application application) {
        super(application);
        studentRepositoryJava = new StudentRepositoryJava(application);
    }

    public void insert(Student... students) {
        studentRepositoryJava.insert(students);
    }

    public void delete(Student student) {
        studentRepositoryJava.delete(student);
    }

    public void update(Student student) {
        studentRepositoryJava.update(student);
    }

    public List<Student> getAll() {
        return studentRepositoryJava.getAll();
    }

    public LiveData<List<Student>> getLiveDataAllStudent() {
        return studentRepositoryJava.getListLiveData();
    }

}
