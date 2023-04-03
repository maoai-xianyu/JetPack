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

import com.mao.jetpack.R;
import com.mao.jetpack.databinding.ActivityAnimatorUiThreeScaleOtherBinding;
import com.maoyan.utils.DimenUtils;

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description  多海报正确显示
 */
public class AnimatorUIThreeOtherActivity extends AppCompatActivity {

    private ActivityAnimatorUiThreeScaleOtherBinding binding;
    private AnimatorSet mAnimatorSet;

    private final long time = 4000;
    private boolean roll = false;

    private ImageView imageChange1;
    private ImageView imageChange2;
    private ImageView imageChange3;

    private int count = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAnimatorUiThreeScaleOtherBinding.inflate(LayoutInflater.from(this));
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
        ValueAnimatorUtil.resetDurationScaleIfDisable();
        mAnimatorSet.start();

    }

    @SuppressLint("Recycle")
    private void startAn() {
        // 动画间隔
        ValueAnimator animatorStart = ValueAnimator.ofFloat(0f, 1f);
        animatorStart.setDuration(4000);

        AnimatorSet animatorSet = new AnimatorSet();

        // 缩放 x
        // 1 -> 0.8
        float scale1To8 = 0.8f;
        ValueAnimator vxS1 = ValueAnimator.ofFloat(1f, scale1To8);
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
        ValueAnimator vT1 = ValueAnimator.ofFloat(0, DimenUtils.dp2px(20.6f));
        vT1.setDuration(time);
        vT1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange1.setTranslationX(v);
            }
        });

        //透明度
        ValueAnimator vA1 = ValueAnimator.ofFloat(1, 0.3f);
        vA1.setDuration(time);
        vA1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange1.setAlpha(v);
            }
        });

        // 变化显示
        ValueAnimator vC1 = ValueAnimator.ofFloat(1, -2);
        vC1.setDuration(time);
        vC1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange1.setTranslationZ(v);
            }
        });


        // 缩放 x
        // 0.9 -> 1
        float scale9To1 = 1.111111111111111f;
        ValueAnimator vxS2 = ValueAnimator.ofFloat(1f, scale9To1);
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
        ValueAnimator vT2 = ValueAnimator.ofFloat(0, -DimenUtils.dp2px(10.3f));
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


        // 第三张海报
        // 0.8 -> 0.9
        float scale8To9 = 1.125f;
        ValueAnimator vxS3 = ValueAnimator.ofFloat(1, scale8To9);
        vxS3.setDuration(time);
        vxS3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (float) animation.getAnimatedValue();
                imageChange3.setScaleX(animatedValue);
                imageChange3.setScaleY(animatedValue);
            }
        });

        // 平移
        ValueAnimator vT3 = ValueAnimator.ofFloat(0, -DimenUtils.dp2px(10.3f));
        vT3.setDuration(time);
        vT3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange3.setTranslationX(v);
            }
        });

        //透明度
        ValueAnimator vA3 = ValueAnimator.ofFloat(0.3f, 0.88f);
        vA3.setDuration(time);
        vA3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange3.setAlpha(v);
            }
        });

        // 变化显示
        ValueAnimator vC3 = ValueAnimator.ofFloat(-2, -1);
        vC3.setDuration(time);
        vC3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange3.setTranslationZ(v);
            }
        });


        animatorSet.playTogether(vxS1, vT1, vA1, vC1, vxS2, vT2, vA2, vC2, vxS3, vT3, vA3, vC3);
//        animatorSet.playTogether(vxS1, vA1, vC1, vxS2, vA2, vC2);
        mAnimatorSet.playSequentially(animatorStart, animatorSet);


        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(DimenUtils.dp2px(106),
                        DimenUtils.dp2px(152));
                params1.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
                FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(DimenUtils.dp2px(95.4f),
                        DimenUtils.dp2px(136.8f));
                params2.rightMargin = DimenUtils.dp2px(5f);
                params2.gravity = Gravity.END | Gravity.CENTER_VERTICAL;

                FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(DimenUtils.dp2px(84.8f),
                        DimenUtils.dp2px(121.6f));
                params3.gravity = Gravity.END | Gravity.CENTER_VERTICAL;


                if (count == 1) {
                    binding.image2.setTranslationZ(1);
                    binding.image2.setAlpha(1f);
                    binding.image2.setTranslationX(0);
                    binding.image2.setScaleX(1);
                    binding.image2.setScaleY(1);

                    binding.image3.setTranslationZ(-1);
                    binding.image3.setAlpha(0.88f);
                    binding.image3.setTranslationX(0);
                    binding.image3.setScaleX(1);
                    binding.image3.setScaleY(1);

                    binding.image1.setTranslationZ(-2);
                    binding.image1.setAlpha(0.3f);
                    binding.image1.setTranslationX(0);
                    binding.image1.setScaleX(1);
                    binding.image1.setScaleY(1);

                    binding.image1.setImageResource(R.drawable.a_image_4);


                    binding.image1.setLayoutParams(params3);
                    binding.image2.setLayoutParams(params1);
                    binding.image3.setLayoutParams(params2);

                    imageChange1 = binding.image2;
                    imageChange2 = binding.image3;
                    imageChange3 = binding.image1;

                } else if (count == 2) {


                    binding.image3.setTranslationZ(1);
                    binding.image3.setAlpha(1f);
                    binding.image3.setTranslationX(0);
                    binding.image3.setScaleX(1);
                    binding.image3.setScaleY(1);

                    binding.image1.setTranslationZ(-1);
                    binding.image1.setAlpha(0.88f);
                    binding.image1.setTranslationX(0);
                    binding.image1.setScaleX(1);
                    binding.image1.setScaleY(1);

                    binding.image2.setTranslationZ(-2);
                    binding.image2.setAlpha(0.3f);
                    binding.image2.setTranslationX(0);
                    binding.image2.setScaleX(1);
                    binding.image2.setScaleY(1);


                    binding.image1.setLayoutParams(params2);
                    binding.image2.setLayoutParams(params3);
                    binding.image3.setLayoutParams(params1);


                    imageChange1 = binding.image3;
                    imageChange2 = binding.image1;
                    imageChange3 = binding.image2;

                } else {


                    binding.image1.setTranslationZ(1);
                    binding.image1.setAlpha(1f);
                    binding.image1.setTranslationX(0);
                    binding.image1.setScaleX(1);
                    binding.image1.setScaleY(1);

                    binding.image2.setTranslationZ(-1);
                    binding.image2.setAlpha(0.88f);
                    binding.image2.setTranslationX(0);
                    binding.image2.setScaleX(1);
                    binding.image2.setScaleY(1);

                    binding.image3.setTranslationZ(-2);
                    binding.image3.setAlpha(0.3f);
                    binding.image3.setTranslationX(0);
                    binding.image3.setScaleX(1);
                    binding.image3.setScaleY(1);


                    binding.image1.setLayoutParams(params1);
                    binding.image2.setLayoutParams(params2);
                    binding.image3.setLayoutParams(params3);


                    imageChange1 = binding.image1;
                    imageChange2 = binding.image2;
                    imageChange3 = binding.image3;


                    count = 0;
                }

                count++;


                mAnimatorSet.start();

            }
        });
    }


}
