package com.mao.jetpack.ui.text;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.databinding.ActivityAnimatorTotalBinding;

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description
 */
public class AnimatorTotalActivity extends AppCompatActivity {


    private ActivityAnimatorTotalBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAnimatorTotalBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

//        anOne();
//        anTwo();
        anThree();
//        anFive();


    }


    private void anOne() {


        AnimationSet animationSet = new AnimationSet(true);


       /* ScaleAnimation flScaleAn = new ScaleAnimation(1, 0.9f, 1, 0.9f,
                ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        flScaleAn.setDuration(3000);
        flScaleAn.setRepeatCount(2);
        flScaleAn.setRepeatMode(Animation.REVERSE);
        binding.flScore.startAnimation(flScaleAn);*/


        RotateAnimation flRotateAn = new RotateAnimation(0, 8, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        flRotateAn.setDuration(2000);
        flRotateAn.setRepeatCount(2);
        flRotateAn.setRepeatMode(Animation.RESTART);
        binding.flScore.startAnimation(flRotateAn);
    }

    private void anTwo() {

        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation flScaleAn = new ScaleAnimation(1, 0.9f, 1, 0.9f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        flScaleAn.setDuration(2000);
        flScaleAn.setRepeatCount(2);
        flScaleAn.setRepeatMode(Animation.REVERSE);

        RotateAnimation flRotateAn = new RotateAnimation(0, 8, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        flRotateAn.setDuration(2000);

        flRotateAn.setRepeatCount(2);
        flRotateAn.setRepeatMode(Animation.REVERSE);


        animationSet.addAnimation(flScaleAn);
        animationSet.addAnimation(flRotateAn);
        animationSet.setFillBefore(true);
        binding.flScore.startAnimation(animationSet);

    }


    private void anThree() {

        final boolean[] isChangeText = {true};
        final int[] times = {0};

        ValueAnimator animatorStart = ValueAnimator.ofFloat(0f, 1f);
        animatorStart.setDuration(3000);

        ValueAnimator flScaleAn = ValueAnimator.ofFloat(1f, 0.9f, 1f);
        flScaleAn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                binding.flScore.setScaleX(value);
                binding.flScore.setScaleY(value);
            }
        });
        flScaleAn.setDuration(4000);

        ValueAnimator flRotate = ValueAnimator.ofFloat(0f, 8f, 0f);
        flRotate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                binding.flScore.setRotation(value);
            }
        });
        flRotate.setDuration(4000);

        ValueAnimator tvAlphaBefore = ValueAnimator.ofFloat(1f, 0f);
        tvAlphaBefore.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                binding.tvScore.setAlpha(value);
            }
        });
        tvAlphaBefore.setDuration(2000);

        ValueAnimator tvAlphaAfter = ValueAnimator.ofFloat(0f, 1f);
        tvAlphaAfter.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                binding.tvScore.setAlpha(value);
            }
        });
        tvAlphaAfter.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                binding.tvScore.setText(isChangeText[0] ? "成功" : "积分");
                isChangeText[0] = !isChangeText[0];
            }
        });
        tvAlphaAfter.setDuration(2000);

        AnimatorSet tvSet = new AnimatorSet();
        tvSet.playSequentially(tvAlphaBefore, tvAlphaAfter);

        ValueAnimator tvScale = ValueAnimator.ofFloat(1f, 0.7f, 1f);
        tvScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                binding.tvScore.setScaleX(value);
                binding.tvScore.setScaleY(value);
            }
        });
        tvScale.setDuration(4000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(flScaleAn, flRotate, tvSet, tvScale);

        AnimatorSet animatorSetAll = new AnimatorSet();
        animatorSetAll.playSequentially(animatorStart, animatorSet);
        animatorSetAll.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (times[0] >= 3) {

                } else {
                    animatorSetAll.start();
                }
                times[0]++;
            }
        });
        animatorSetAll.start();


    }


    private void anFive() {

        final boolean[] isChangeText = {true};

        ValueAnimator tvAlphaBefore = ValueAnimator.ofFloat(1f, 0f);
        tvAlphaBefore.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                binding.tvScore.setAlpha(value);
            }
        });
        tvAlphaBefore.setDuration(2000);


        ValueAnimator tvAlphaAfter = ValueAnimator.ofFloat(0f, 1f);
        tvAlphaAfter.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                binding.tvScore.setAlpha(value);
            }
        });
        tvAlphaAfter.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                binding.tvScore.setText(isChangeText[0] ? "成功" : "积分");
                isChangeText[0] = !isChangeText[0];
            }
        });
        tvAlphaAfter.setDuration(2000);

        AnimatorSet tvSet = new AnimatorSet();
        tvSet.playSequentially(tvAlphaBefore, tvAlphaAfter);
        /*tvSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                tvSet.start();
            }
        });
        tvSet.start();*/

        ValueAnimator tvScale = ValueAnimator.ofFloat(1f, 0.7f, 1f);
        tvScale.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                binding.tvScore.setScaleX(value);
                binding.tvScore.setScaleY(value);
            }
        });
        tvScale.setDuration(4000);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(tvSet, tvScale);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                set.start();
            }
        });
        set.start();
    }

    /*private void anFour() {
        // 定义两个文本框
        TextView textView1 = findViewById(R.id.text_view_1);
        TextView textView2 = findViewById(R.id.text_view_2);

        // 定义一个 ValueAnimator 控制透明度从不透明到透明再到不透明
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f, 1f);
        animator.setDuration(2000); // 动画时长为 2 秒

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                // 获取当前动画值
                float alpha = (float) valueAnimator.getAnimatedValue();

                // 设置文本透明度
                textView1.setAlpha(alpha);
                textView2.setAlpha(1 - alpha);

                // 如果透明度为 0，切换文本
                if(alpha == 0){
                    String temp = textView1.getText().toString();
                    textView1.setText(textView2.getText());
                    textView2.setText(temp);
                }
            }
        });

        animator.start(); // 启动动画
    }*/
}
