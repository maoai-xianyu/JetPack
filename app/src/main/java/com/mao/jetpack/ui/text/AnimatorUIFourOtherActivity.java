package com.mao.jetpack.ui.text;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mao.jetpack.databinding.ActivityAnimatorUiFourBinding;
import com.maoyan.utils.DimenUtils;

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description 多海报，使用 属性动画  还是显示有问题
 */
public class AnimatorUIFourOtherActivity extends AppCompatActivity {

    private ActivityAnimatorUiFourBinding binding;
    private AnimatorSet mAnimatorSet;

    private final long time = 4000;
    private boolean roll = false;

    private ImageView imageChange1;
    private ImageView imageChange2;
    private ImageView imageChange3;

    private float scale1To8 = 0.8f; // 1 -> 0.8
    private float scale8To9 = 1.125f; // 0.8 -> 0.9
    private float scale9To1 = 1.111111111111111f; // 0.9 -> 1


    private int count = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAnimatorUiFourBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.image1.setTranslationZ(1);
        binding.image1.setAlpha(1f);

        binding.image2.setTranslationZ(-1);
        binding.image2.setAlpha(0.88f);

        binding.image3.setTranslationZ(-2);
        binding.image3.setAlpha(0.3f);

        imageChange1 = binding.image1;
        imageChange2 = binding.image2;
        imageChange3 = binding.image3;

        mAnimatorSet = new AnimatorSet();
        startAn();
        mAnimatorSet.start();

    }


    @SuppressLint("Recycle")
    private void startAn() {
        // 动画间隔
        ObjectAnimator animatorStart = ObjectAnimator.ofFloat(binding.fl, "alpha", 1f, 1f);
        animatorStart.setDuration(4000);

        AnimatorSet animatorSet = new AnimatorSet();

        // 缩放 x
        ObjectAnimator vxS1 = ObjectAnimator.ofFloat(imageChange1, "scaleX", 1f, scale1To8);
        vxS1.setDuration(time);

        ObjectAnimator vyS1 = ObjectAnimator.ofFloat(imageChange1, "scaleY", 1f, scale1To8);
        vyS1.setDuration(time);


        // 平移
        ValueAnimator vT1 = ObjectAnimator.ofFloat(imageChange1, "translationX", 0, DimenUtils.dp2px(20.6f));
        vT1.setDuration(time);

        //透明度
        ObjectAnimator vA1 = ObjectAnimator.ofFloat(imageChange1, "alpha", 1, 0.3f);
        vA1.setDuration(time);

        // 变化显示
        ObjectAnimator vC1 = ObjectAnimator.ofFloat(imageChange1, "translationZ", 1f, -2f);
        vC1.setDuration(time);


        // 缩放 x
        ObjectAnimator vxS2 = ObjectAnimator.ofFloat(imageChange2, "scaleX", 1f, scale9To1);
        vxS2.setDuration(time);

        ObjectAnimator vyS2 = ObjectAnimator.ofFloat(imageChange2, "scaleY", 1f, scale9To1);
        vyS2.setDuration(time);

        // 平移
        ObjectAnimator vT2 = ObjectAnimator.ofFloat(imageChange2, "translationX", 0, -DimenUtils.dp2px(10.3f));
        vT2.setDuration(time);

        //透明度
        ValueAnimator vA2 = ObjectAnimator.ofFloat(imageChange2, "alpha", 0.88f, 1f);
        vA2.setDuration(time);

        // 变化显示
        ObjectAnimator vC2 = ObjectAnimator.ofFloat(imageChange2, "translationZ", -1f, 1f);
        vC2.setDuration(time);


        // 第三张海报
        ObjectAnimator vxS3 = ObjectAnimator.ofFloat(imageChange3, "scaleX", 1f, scale8To9);
        vxS3.setDuration(time);

        ObjectAnimator vyS3 = ObjectAnimator.ofFloat(imageChange3, "scaleY", 1f, scale8To9);
        vyS3.setDuration(time);


        // 平移
        ObjectAnimator vT3 = ObjectAnimator.ofFloat(imageChange3, "translationX", 0, -DimenUtils.dp2px(10.3f));
        vT3.setDuration(time);

        //透明度
        ObjectAnimator vA3 = ObjectAnimator.ofFloat(imageChange3, "alpha", 0.3f, 0.88f);
        vA3.setDuration(time);

        // 变化显示
        ObjectAnimator vC3 = ObjectAnimator.ofFloat(imageChange3,"translationZ",-2f, -1f);
        vC3.setDuration(time);


        animatorSet.playTogether(vxS1, vyS1, vT1, vA1, vC1, vxS2, vyS2, vT2, vA2, vC2, vxS3, vyS3, vT3, vA3, vC3);
        mAnimatorSet.playSequentially(animatorStart, animatorSet);


        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(DimenUtils.dp2px(106), DimenUtils.dp2px(152));
                params1.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
                FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(DimenUtils.dp2px(95.4f), DimenUtils.dp2px(136.8f));
                params2.rightMargin = DimenUtils.dp2px(5f);
                params2.gravity = Gravity.END | Gravity.CENTER_VERTICAL;

                FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(DimenUtils.dp2px(84.8f), DimenUtils.dp2px(121.6f));
                params3.gravity = Gravity.END | Gravity.CENTER_VERTICAL;


                if (count == 1) {
                    /*binding.image2.setTranslationZ(1f);
                    binding.image2.setAlpha(1f);
                    binding.image2.setTranslationX(0);
                    binding.image2.setScaleX(1f);
                    binding.image2.setScaleY(1f);

                    binding.image3.setTranslationZ(-1f);
                    binding.image3.setAlpha(0.88f);
                    binding.image3.setTranslationX(0f);
                    binding.image3.setScaleX(1);
                    binding.image3.setScaleY(1);

                    binding.image1.setTranslationZ(-2f);
                    binding.image1.setAlpha(0.3f);
                    binding.image1.setTranslationX(0f);
                    binding.image1.setScaleX(1);
                    binding.image1.setScaleY(1);


                    binding.image1.setLayoutParams(params3);
                    binding.image2.setLayoutParams(params1);
                    binding.image3.setLayoutParams(params2);*/

                    imageChange1 = binding.image2;
                    imageChange2 = binding.image3;
                    imageChange3 = binding.image1;



                } else if (count == 2) {


                    /*binding.image3.setTranslationZ(1f);
                    binding.image3.setAlpha(1f);
                    binding.image3.setTranslationX(0f);
                    binding.image3.setScaleX(1f);
                    binding.image3.setScaleY(1f);

                    binding.image1.setTranslationZ(-1f);
                    binding.image1.setAlpha(0.88f);
                    binding.image1.setTranslationX(0f);
                    binding.image1.setScaleX(1f);
                    binding.image1.setScaleY(1f);

                    binding.image2.setTranslationZ(-2f);
                    binding.image2.setAlpha(0.3f);
                    binding.image2.setTranslationX(0f);
                    binding.image2.setScaleX(1f);
                    binding.image2.setScaleY(1f);


                    binding.image1.setLayoutParams(params2);
                    binding.image2.setLayoutParams(params3);
                    binding.image3.setLayoutParams(params1);*/


                    imageChange1 = binding.image3;
                    imageChange2 = binding.image1;
                    imageChange3 = binding.image2;



                } else {

                    /*binding.image1.setTranslationZ(1f);
                    binding.image1.setAlpha(1f);
                    binding.image1.setTranslationX(0f);
                    binding.image1.setScaleX(1f);
                    binding.image1.setScaleY(1f);

                    binding.image2.setTranslationZ(-1f);
                    binding.image2.setAlpha(0.88f);
                    binding.image2.setTranslationX(0f);
                    binding.image2.setScaleX(1f);
                    binding.image2.setScaleY(1f);

                    binding.image3.setTranslationZ(-2);
                    binding.image3.setAlpha(0.3f);
                    binding.image3.setTranslationX(0f);
                    binding.image3.setScaleX(1f);
                    binding.image3.setScaleY(1f);

                    binding.image1.setLayoutParams(params1);
                    binding.image2.setLayoutParams(params2);
                    binding.image3.setLayoutParams(params3);*/


                    imageChange1 = binding.image1;
                    imageChange2 = binding.image2;
                    imageChange3 = binding.image3;

                    count = 0;
                }

                imageChange1.setTranslationZ(1f);
                imageChange1.setAlpha(1f);
                imageChange1.setTranslationX(0);
                imageChange1.setScaleX(1f);
                imageChange1.setScaleY(1f);

                imageChange2.setTranslationZ(-1f);
                imageChange2.setAlpha(0.88f);
                imageChange2.setTranslationX(0f);
                imageChange2.setScaleX(1);
                imageChange2.setScaleY(1);

                imageChange3.setTranslationZ(-2f);
                imageChange3.setAlpha(0.3f);
                imageChange3.setTranslationX(0f);
                imageChange3.setScaleX(1);
                imageChange3.setScaleY(1);



                imageChange1.setLayoutParams(params1);
                imageChange2.setLayoutParams(params2);
                imageChange3.setLayoutParams(params3);

                count++;


                mAnimatorSet.start();

            }
        });
    }


}
