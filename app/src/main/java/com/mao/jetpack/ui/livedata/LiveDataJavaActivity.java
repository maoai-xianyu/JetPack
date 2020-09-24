package com.mao.jetpack.ui.livedata;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mao.jetpack.R;

/**
 * @author zhangkun
 * @time 2020/9/24 5:45 PM
 * @Description
 */
class LiveDataJavaActivity extends AppCompatActivity {

    private MutableLiveData<String> liveData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);

        liveData = new MutableLiveData<>();

        // setValue 和 postValue 的区别 有一个线程的概念
        // setValue
        // postValue
        // liveData.setValue("");
        // liveData.postValue("");

        // this 是因为 AppCompatActivity extends FragmentActivity extends ComponentActivity
        // implements  LifecycleOwner 接口
        // 如果是继承Activity，需要单独处理
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

    }
}
