package com.mao.lib_cache.model;

import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;

/**
 * @author zhangkun
 * @time 2020/10/14 11:46 PM
 * @Description
 */
public class Story extends BaseObservable {

    private String name;
    private Integer image;

    public Story(String name, Integer image) {
        this.name = name;
        this.image = image;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }


    @BindingAdapter({"android:src"})
    public static void setImageResource(ImageView imageView,int resource){
        // 可以使用第三方图片加载，设置图片，根据不同的显示进行处理
        imageView.setImageResource(resource);
    }
}
