package com.mao.jetpack.ui.deeplink;

import android.app.Activity;
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
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("maoyan://maoyan.com/movielist"));
                    startActivity(intent);
                } catch (Exception e) {
                    Logger.error("跳转淘宝 奔溃 " + e.toString());
                } finally {
                    Logger.error("finally 跳转猫眼");
                }
            }
        });


        binding.btnRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // 淘宝
                    //openTaoBao(v);
                    // 天猫
                    toTianMao("天猫");
                    // 微博
                    //openSendWeibo(WebJavaActivity.this, "微博");
                    //京东
                    //openJingdong();
                } catch (Exception e) {
                    Logger.error("跳转淘宝 奔溃 ${e.message}" + e.toString());
                } finally {

                    Logger.error("finally 跳转淘宝 ");
                }
            }
        });
    }

    public void openTaoBao(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("taobao://item.taobao.com/item.html?id=41700658839"));
        //v.getContext().startActivity(intent);
        startActivity(intent);
    }

    private void toTianMao(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        String id = getUrlParam(url, "id");
        String tmall_url = "tmall://tmallclient/?{\"action\":\"item:id=" + id + "\"}";
        Uri uri = Uri.parse(tmall_url);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 获取URL中的某个参数
     */
    public static String getUrlParam(String url, String name) {
        Uri uri = Uri.parse(url);
        return uri.getQueryParameter(name);
    }


    // 微博 需要  sinaweibo://sendweibo 单用  sinaweibo:// 跳转不了
    public void openSendWeibo(Activity activity, String content) {
        if (activity == null || null == content) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addCategory("android.intent.category.DEFAULT");
        //intent.setData(Uri.parse("sinaweibo://sendweibo?content=" + URLEncoder.encode(content)));
        //成功
        intent.setData(Uri.parse("sinaweibo://sendweibo?params=%7B%22category%22%3A%22jump%22%2C%22des%22%3A%22m%22%2C%22url%22%3A%22https%3A%2F%2Fccc-x.jd.com%2Fdsp%2Fcl%3Fposid%3D1999%26v%3D707%26union_id%3D1000023385%26pid%3D3232%26tagid%3D157312%26didmd5%3D__IMEI__%26idfamd5%3D__IDFA__%26did%3D__IMEIIMEI__%26idfa%3D__IDFAIDFA__%26oaid%3D__OAID__%26caid%3D__CAID__%26to%3Dhttps%253A%252F%252Fprodev.m.jd.com%252Fmall%252Factive%252FC7T8PzEjGKL7WiXAJmATw4YuNeZ%252Findex.html%253Fad_od%253D1%2526babelChannel%253Dttt13%22%2C%22m_param%22%3A%7B%22jdv%22%3A%22122270672%7Ckong%7Ct_1000023385_157312%7Czssc%7Cd36d13b9-61c4-4fdf-b7f2-11dbc28d14dd-p_1999-pr_3232-at_157312%22%7D%2C%22keplerFrom%22%3A%221%22%2C%22kepler_param%22%3A%7B%22source%22%3A%22kepler-open%22%2C%22otherData%22%3A%7B%22channel%22%3A%22b4dc3278288f4a25982ccdec07ebdc41%22%7D%7D%7D"));

        // 奔溃
        //intent.setData(Uri.parse("sinaweibo://virtual?params=%7B%22category%22%3A%22jump%22%2C%22des%22%3A%22m%22%2C%22url%22%3A%22https%3A%2F%2Fccc-x.jd.com%2Fdsp%2Fcl%3Fposid%3D1999%26v%3D707%26union_id%3D1000023385%26pid%3D3232%26tagid%3D157312%26didmd5%3D__IMEI__%26idfamd5%3D__IDFA__%26did%3D__IMEIIMEI__%26idfa%3D__IDFAIDFA__%26oaid%3D__OAID__%26caid%3D__CAID__%26to%3Dhttps%253A%252F%252Fprodev.m.jd.com%252Fmall%252Factive%252FC7T8PzEjGKL7WiXAJmATw4YuNeZ%252Findex.html%253Fad_od%253D1%2526babelChannel%253Dttt13%22%2C%22m_param%22%3A%7B%22jdv%22%3A%22122270672%7Ckong%7Ct_1000023385_157312%7Czssc%7Cd36d13b9-61c4-4fdf-b7f2-11dbc28d14dd-p_1999-pr_3232-at_157312%22%7D%2C%22keplerFrom%22%3A%221%22%2C%22kepler_param%22%3A%7B%22source%22%3A%22kepler-open%22%2C%22otherData%22%3A%7B%22channel%22%3A%22b4dc3278288f4a25982ccdec07ebdc41%22%7D%7D%7D"));
        activity.startActivity(intent);
    }


    // 跳转京东
    public void openJingdong(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //成功
        intent.setData(Uri.parse("openapp.jdmobile://virtual?params=%7B%22category%22%3A%22jump%22%2C%22des%22%3A%22m%22%2C%22url%22%3A%22https%3A%2F%2Fccc-x.jd.com%2Fdsp%2Fcl%3Fposid%3D1999%26v%3D707%26union_id%3D1000023385%26pid%3D3232%26tagid%3D157312%26didmd5%3D__IMEI__%26idfamd5%3D__IDFA__%26did%3D__IMEIIMEI__%26idfa%3D__IDFAIDFA__%26oaid%3D__OAID__%26caid%3D__CAID__%26to%3Dhttps%253A%252F%252Fprodev.m.jd.com%252Fmall%252Factive%252FC7T8PzEjGKL7WiXAJmATw4YuNeZ%252Findex.html%253Fad_od%253D1%2526babelChannel%253Dttt13%22%2C%22m_param%22%3A%7B%22jdv%22%3A%22122270672%7Ckong%7Ct_1000023385_157312%7Czssc%7Cd36d13b9-61c4-4fdf-b7f2-11dbc28d14dd-p_1999-pr_3232-at_157312%22%7D%2C%22keplerFrom%22%3A%221%22%2C%22kepler_param%22%3A%7B%22source%22%3A%22kepler-open%22%2C%22otherData%22%3A%7B%22channel%22%3A%22b4dc3278288f4a25982ccdec07ebdc41%22%7D%7D%7D"));
        startActivity(intent);
    }



}
