package com.mao.jetpack.ui.reflect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.R;

/**
 * @author zhangkun
 * @time 2022/4/12 10:12
 * @Description
 */
public class ReflectThirdActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect_third);

        Intent intent = getIntent();
        String tests_url = intent.getStringExtra("test_url");


        TextView viewById = findViewById(R.id.tvThree);
        viewById.setText(tests_url + "  点击关闭");
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("tests_url", "这是第三个activity 返回来的数据");

                setResult(RESULT_OK, intent);

                finish();


            }
        });
    }
}
