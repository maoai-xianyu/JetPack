package com.mao.jetpack.ui.livedata;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.mao.jetpack.R;
import com.mao.jetpack.utils.Logger;

/**
 * @author zhangkun
 * @time 2020/9/24 5:45 PM
 * @Description
 */
public class LiveDataJavaActivity extends AppCompatActivity {

    private MutableLiveData<String> liveData;
    private MutableLiveData<String> liveDataString;
    private Button tvButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);

        liveData = new MutableLiveData<>();

        liveDataString = LiveDataBus.getInstance().with("lisan", String.class);

        // 设置参数  setValue 和 postValue 的区别 有一个线程的概念
        // setValue  只能在主线程中使用
        // postValue 可以在任意的线程中使用
        // liveData.setValue("");
        // liveData.postValue("");


        // liveData.postValue("2222222");
        // 先发送，后注册监听，依然可以显示数据，是因为：observe 由生命周期
        // 的回调调用的观察者里面的方法


        // this 是因为 AppCompatActivity extends FragmentActivity extends ComponentActivity
        // implements  LifecycleOwner 接口
        // 如果是继承Activity，需要单独处理
        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                Logger.debug("livedata java 1 " + s);

            }
        });

        liveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                Logger.debug("livedata java 2 " + s);

            }
        });


        tvButton = findViewById(R.id.tvButton);


        initListener();
        LiveDataJavaActivity.this.getLifecycle().addObserver(new MyListener());

    }

    private void initListener() {

        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 给livedata设置参数  有两种方法

              /*  new Thread(new Runnable() {
                    @Override
                    public void run() {*/
                // 只能在主线程使用
                // 如果在子线程会报错 java.lang.IllegalStateException: Cannot invoke setValue on a background thread
                //liveData.setValue("11111");
                   /* }
                });*/

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 可以在任意线程中使用
                        liveData.postValue("2222222");
                    }
                }).start();
            }
        });
    }

    class MyListener implements LifecycleEventObserver {

        @Override
        public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
            // 感知生命周期
            Logger.debug("MyListener  event" + event.name());

        }
    }
}
