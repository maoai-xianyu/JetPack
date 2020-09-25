package com.mao.jetpack.ui.viewmodelfile.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.mao.jetpack.BR;

/**
 * @author zhangkun
 * @time 2020/9/24 9:33 PM
 * @Description
 */
public class UserLiveData extends BaseObservable {
    private String name;
    private int age;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }


    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }


    @Override
    public String toString() {
        return "UserLiveData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
