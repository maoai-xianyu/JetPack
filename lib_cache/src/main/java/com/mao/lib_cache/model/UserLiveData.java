package com.mao.lib_cache.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


/**
 * @author zhangkun
 * @time 2020/9/24 9:33 PM
 * @Description
 */
@Entity(tableName = "tb_user_live_data")
public class UserLiveData extends BaseObservable {
    private String name;
    private int age;

    // 标记主键
    @PrimaryKey
    private Integer u_id;

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

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
