package com.mao.jetpack.ui.text;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.databinding.ActivityAnimatorUiTwoScaleBinding;
import com.maoyan.utils.DimenUtils;

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description
 */
public class AnimatorUiTwoScaleActivity extends AppCompatActivity {

    private ActivityAnimatorUiTwoScaleBinding binding;
    private AnimatorSet mAnimatorSet;

    private final long time = 4000;
    private boolean roll = false;

    private ImageView imageChange1;
    private ImageView imageChange2;

    private int moveLeft;



    private float changeBig = 1.104166666666667f;
    private float changeSmall = 0.905660377358491f;

    private float xC1_1 = 1f;
    private float xC1_2 = changeSmall;
    private float xC2_1 = 1f;
    private float xC2_2 = changeBig;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAnimatorUiTwoScaleBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        imageChange1 = binding.image1;

        moveLeft = 20;
        imageChange2 = binding.image2;
        mAnimatorSet = new AnimatorSet();
        startAn();
        mAnimatorSet.start();

    }

    @SuppressLint("Recycle")
    private void startAn() {
        // 动画间隔
        ValueAnimator animatorStart = ValueAnimator.ofFloat(0f, 1f);
        animatorStart.setDuration(4000);

        AnimatorSet animatorSet = new AnimatorSet();

        // 缩放 x
        ValueAnimator vxS1 = ValueAnimator.ofFloat(xC1_1, xC1_2);
        vxS1.setDuration(time);
        vxS1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                imageChange1.setScaleX(animatedValue);
                imageChange1.setScaleY(animatedValue);
            }
        });

        // 平移
        ValueAnimator vT1 = ValueAnimator.ofFloat(0, DimenUtils.dp2px(15));
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
        ValueAnimator vxS2 = ValueAnimator.ofFloat(xC2_1, xC2_2);
        vxS2.setDuration(time);
        vxS2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                imageChange2.setScaleX(animatedValue);
                imageChange2.setScaleY(animatedValue);
            }
        });

        // 平移
        ValueAnimator vT2 = ValueAnimator.ofFloat(0, -DimenUtils.dp2px(15));
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

        // 变化显示
        ValueAnimator vC2 = ValueAnimator.ofFloat(-1, 1);
        vC2.setDuration(time);
        vC2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange2.setTranslationZ(v);
            }
        });


        animatorSet.playTogether(vxS1, vT1, vA1, vC1,vxS2, vT2, vA2, vC2);
//        animatorSet.playTogether(vxS1, vA1, vC1, vxS2, vA2, vC2);
        mAnimatorSet.playSequentially(animatorStart, animatorSet);


        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(DimenUtils.dp2px(106), DimenUtils.dp2px(152));
                params1.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
                FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(DimenUtils.dp2px(96), DimenUtils.dp2px(136));
                params2.gravity = Gravity.END | Gravity.CENTER_VERTICAL;


                roll = !roll;


                binding.image2.setLayoutParams(roll ? params1 : params2);
                binding.image1.setLayoutParams(roll ? params2 : params1);


        /*        xC1_1 = roll ? changeBig : 1;
                xC1_2 = roll ? 1 : changeSmall;


                xC2_1 = roll ? changeSmall : 1;
                xC2_2 = roll ? 1 : changeBig;
                */

                imageChange1 = roll ? binding.image2 : binding.image1;
                imageChange2 = roll ? binding.image1 : binding.image2;

                mAnimatorSet.start();

            }
        });
    }


}
