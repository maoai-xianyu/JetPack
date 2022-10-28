package com.mao.jetpack.ui.text;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.databinding.ActivityAnimatorUiTwoBinding;
import com.mao.jetpack.utils.Logger;
import com.maoyan.utils.DimenUtils;

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description
 */
public class AnimatorUTwoActivity extends AppCompatActivity {

    private ActivityAnimatorUiTwoBinding binding;
    private AnimatorSet mAnimatorSet;

    private final long time = 4000;
    private boolean roll = false;

    private ImageView imageChange1;
    private ImageView imageChange2;

    private int moveX = 15;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAnimatorUiTwoBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());



        imageChange1 = binding.image1;
        imageChange2 = binding.image2;
        mAnimatorSet = new AnimatorSet();
        startAn3();
        mAnimatorSet.start();

    }

    @SuppressLint("Recycle")
    private void startAn3() {
        // 动画间隔
        ValueAnimator animatorStart = ValueAnimator.ofFloat(0f, 1f);
        animatorStart.setDuration(4000);

        AnimatorSet animatorSet = new AnimatorSet();

        // 缩放 x
        ValueAnimator vxS1 = ValueAnimator.ofInt(DimenUtils.dp2px(106), DimenUtils.dp2px(96));
        vxS1.setDuration(time);
        vxS1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageChange1.getLayoutParams();
                layoutParams.width = v;
                imageChange1.setLayoutParams(layoutParams);
            }
        });

        // 缩放 y
        ValueAnimator vyS1 = ValueAnimator.ofInt(DimenUtils.dp2px(152), DimenUtils.dp2px(136));
        vyS1.setDuration(time);
        vyS1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageChange1.getLayoutParams();
                layoutParams.height = v;
                imageChange1.setLayoutParams(layoutParams);
            }
        });

        // 平移
        ValueAnimator vT1 = ValueAnimator.ofFloat(0, DimenUtils.dp2px(18));
        vT1.setDuration(time);
        vT1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange1.setTranslationX(v);
            }
        });

        //透明度
        ValueAnimator vA1 = ValueAnimator.ofFloat(1, 0.88f);
        vA1.setDuration(time);
        vA1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange1.setAlpha(v);
            }
        });

        // 变化显示
        ValueAnimator vC1 = ValueAnimator.ofFloat(1, -1);
        vC1.setDuration(time);
        vC1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange1.setTranslationZ(v);
            }
        });





        // 缩放 x
        ValueAnimator vxS2 = ValueAnimator.ofInt(DimenUtils.dp2px(96), DimenUtils.dp2px(106));
        vxS2.setDuration(time);
        vxS2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageChange2.getLayoutParams();
                layoutParams.width = v;
                imageChange2.setLayoutParams(layoutParams);
            }
        });

        // 缩放 y
        ValueAnimator vyS2 = ValueAnimator.ofInt(DimenUtils.dp2px(136), DimenUtils.dp2px(152));
        vyS2.setDuration(time);
        vyS2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageChange2.getLayoutParams();
                layoutParams.height = v;
                imageChange2.setLayoutParams(layoutParams);
            }
        });

        // 平移
        ValueAnimator vT2 = ValueAnimator.ofFloat(0, -DimenUtils.dp2px(8f));
        vT2.setDuration(time);
        vT2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange2.setTranslationX(v);
            }
        });


        //透明度
        ValueAnimator vA2 = ValueAnimator.ofFloat(0.88f, 1f);
        vA2.setDuration(time);
        vA2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange2.setAlpha(v);
            }
        });


        ValueAnimator vC2 = ValueAnimator.ofFloat(-1, 1);
        vC2.setDuration(time);
        vC2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange2.setTranslationZ(v);
            }
        });

        //animatorSet.playTogether(vxS1, vyS1,vT1, vA1,vC1);
        animatorSet.playTogether(vxS2, vyS2, vA2,vC2);
        mAnimatorSet.playSequentially(animatorStart, animatorSet,vT2);


        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                roll = !roll;



                binding.image1.post(new Runnable() {
                    @Override
                    public void run() {

                        int width = binding.image1.getWidth();

                        float x = binding.image1.getX();
                        float translationX = binding.image1.getTranslationX();

                        Logger.error(" width  = "+width +" x = "+x +" translationX "+translationX);


                       /* int i = DimenUtils.dp2px(82) - (width + x);
                        Logger.error("i" + i);*/



                        int width1 = binding.fl.getWidth();
                        float x1 = binding.fl.getX();
                        float translationX1 = binding.fl.getTranslationX();

                        Logger.error(" width  = "+width +" x = "+x +" translationX");


                    }
                });
                
                
                


                //mAnimatorSet.start();
            }
        });

    }




}
