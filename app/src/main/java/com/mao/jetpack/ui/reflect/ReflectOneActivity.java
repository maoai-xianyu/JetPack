package com.mao.jetpack.ui.reflect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.R;
import com.mao.jetpack.ui.reflect.inject.InjectUtils;
import com.mao.jetpack.ui.reflect.inject.InjectView;

/**
 * @author zhangkun
 * @time 2022/4/12 10:12
 * @Description
 */
public class ReflectOneActivity extends AppCompatActivity {


    @InjectView(R.id.tvOne)
    TextView textOne;

    @InjectView(R.id.tvToSecond)
    TextView textTwo;

    @InjectView(R.id.tvToThird)
    TextView textThird;

    TextView txThreeDesc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect_one);
        InjectUtils.injectView(this);
        txThreeDesc = findViewById(R.id.tvShowDesc);
        textOne.setText("反射注解获取数据");


        textTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //  LifecycleOwner com.mao.jetpack.ui.reflect.ReflectOneActivity@3f11cee is attempting to register while current state is RESUMED.
        //  LifecycleOwners must call register before they are STARTED.
        //  需要再 onCreate 创建好
        ActivityResultLauncher<Intent> intentActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {

                if (result.getData() != null && result.getResultCode() == RESULT_OK) {
                    String test_url = result.getData().getStringExtra("tests_url");
                    txThreeDesc.setText(test_url);
                }

            }
        });

        textThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReflectOneActivity.this, ReflectThirdActivity.class);

                intent.putExtra("test_url", "测试数据");


                intentActivityResultLauncher.launch(intent);

            }
        });


    }
}
