package com.mao.jetpack.ui.reflect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.R;
import com.mao.jetpack.ui.reflect.inject.InjectUtils;
import com.mao.jetpack.ui.reflect.inject.InjectView;
import com.mao.jetpack.ui.reflect.model.UserParcelable;
import com.mao.jetpack.ui.reflect.model.UserSerializable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;

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


    private static final int WEEK_DAY_ONE = 1;
    private static final int WEEK_DAY_TWO = 2;
    @WeekDay
    private int currentWeekday;

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


                ArrayList<UserParcelable> userParcelableList = new ArrayList<>();

                userParcelableList.add(new UserParcelable("Jett"));
                Intent intent = new Intent(ReflectOneActivity.this, ReflectSecondActivity.class)
                        .putExtra("name", "Lance")
                        .putExtra("attr","帅")
                        .putExtra("array", new int[]{1, 2, 3, 4, 5, 6})
                        .putExtra("userParcelable", new UserParcelable("Lance"))
                        .putExtra("userParcelables", new UserParcelable[]{new UserParcelable("Alvin")})
                        .putExtra("users",new UserSerializable[]{new UserSerializable("Jett")})
                        .putExtra("strs",new String[]{"1","2"})
                        .putParcelableArrayListExtra("userParcelableList", userParcelableList);
                startActivity(intent);

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

        setWeekday(WEEK_DAY_ONE);

    }

    @IntDef({WEEK_DAY_ONE, WEEK_DAY_TWO})
    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface WeekDay {
    }


    private void setWeekday(@WeekDay int weekday) {
        currentWeekday = weekday;
    }


}
