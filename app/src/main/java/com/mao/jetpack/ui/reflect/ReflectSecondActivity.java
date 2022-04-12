package com.mao.jetpack.ui.reflect;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.R;
import com.mao.jetpack.ui.reflect.inject.Autowired;
import com.mao.jetpack.ui.reflect.inject.InjectUtils;
import com.mao.jetpack.ui.reflect.model.UserParcelable;
import com.mao.jetpack.ui.reflect.model.UserSerializable;
import com.mao.jetpack.utils.Logger;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhangkun
 * @time 2022/4/12 10:12
 * @Description
 */
public class ReflectSecondActivity extends AppCompatActivity {

    String k;

    @Autowired
    String name;

    @Autowired("attr")
    String attr;

    @Autowired
    int[] array;

    @Autowired
    UserParcelable userParcelable;

    @Autowired
    UserParcelable[] userParcelables;

    @Autowired
    List<UserParcelable> userParcelableList;

    @Autowired("users")
    UserSerializable[] userSerializables;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect_second);
        InjectUtils.injectAutowired(this);

        TextView tvSecond = findViewById(R.id.tvSecond);
        tvSecond.setText(toString());
        Logger.error(toString());
    }


    @Override
    public String toString() {
        return "SecondActivity{" +
                "name='" + name + '\'' +
                ", attr='" + attr + '\'' +
                ", array=" + Arrays.toString(array) +
                ", userParcelable=" + userParcelable +
                ", userParcelables=" + Arrays.toString(userParcelables) +
                ", userParcelableList=" + userParcelableList +
                ", userSerializables=" + Arrays.toString(userSerializables) +
                '}';
    }
}
