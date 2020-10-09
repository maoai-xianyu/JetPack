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
@Entity(tableName = "tb_user_login")
public class UserLogin extends BaseObservable {
    private String account;
    private String password;

    // 标记主键
    @PrimaryKey(autoGenerate = true)
    private Integer u_id;

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    @Bindable
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
        notifyPropertyChanged(BR.account);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", u_id=" + u_id +
                '}';
    }
}
