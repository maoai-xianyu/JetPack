package com.mao.jetpack.ui.deeplink;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.databinding.ActivityWebBinding;
import com.mao.jetpack.utils.Logger;

/**
 * @author zhangkun
 * @time 2021/4/26 18:37
 * @Description
 */
public class WebJavaActivity extends AppCompatActivity {

    ActivityWebBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWebBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new  Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("maoyan://maoyan.com/movielist"));
                    startActivity(intent);
                } catch (Exception e) {
                    Logger.error("跳转淘宝 奔溃 " +e.toString());
                } finally {
                    Logger.error("finally 跳转猫眼");
                }
            }
        });


        binding.btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("taobao://item.taobao.com/item.html?id=41700658839"));
                    v.getContext().startActivity(intent);
                } catch (Exception e) {
                    Logger.error("跳转淘宝 奔溃 ${e.message}" +e.toString());
                } finally {

                    Logger.error("finally 跳转淘宝 ");
                }
            }
        });
    }
}
