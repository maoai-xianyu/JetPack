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

import com.mao.jetpack.databinding.ActivityAnimatorUiBinding;
import com.mao.jetpack.utils.Logger;
import com.maoyan.utils.DimenUtils;

/**
 * @author zhangkun
 * @time 2022/10/24 16:56
 * @Description
 */
public class AnimatorUiActivity extends AppCompatActivity {

    private ActivityAnimatorUiBinding binding;
    private AnimatorSet mAnimatorSet;
    private AnimatorSet mAnimatorSetTwo;

    private final long time = 4000;
    private boolean roll = false;
    private int movieXv1_0 = 0;
    private int movieXv1_1 = 30;
    private int movieXv2_0 = 0;
    private int movieXv2_1 = -30;

    private ImageView imageChange1;
    private ImageView imageChange2;


    private ImageView imageChange3;
    private ImageView imageChange4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAnimatorUiBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());


        //startAn(binding.image1, binding.image2);
//        startAn2(binding.image1, binding.image2);


        imageChange1 = binding.image1;
        imageChange2 = binding.image2;
        mAnimatorSet = new AnimatorSet();
        startAn3();
        mAnimatorSet.start();




       /* binding.image4.setAlpha(0.88f);


        binding.image4.setPivotX(DimenUtils.dp2px(82));
        binding.image4.setPivotY(binding.image4.getPivotY() +DimenUtils.dp2px(54));
        binding.image4.setScaleX(0.9f);
        binding.image4.setScaleY(0.9f);
        imageChange3 = binding.image3;

        imageChange4 = binding.image4;
        mAnimatorSetTwo = new AnimatorSet();
        startAn4();
        mAnimatorSetTwo.start();
*/
    }

    @SuppressLint("Recycle")
    private void startAn(ImageView image1, ImageView image2) {
        mAnimatorSet = new AnimatorSet();

        // 动画间隔
        ValueAnimator animatorStart = ValueAnimator.ofFloat(0f, 1f);
        animatorStart.setDuration(4000);

        AnimatorSet animatorSet = new AnimatorSet();

        // 缩放
        ValueAnimator vS1 = ValueAnimator.ofFloat(1, 0.9f);
        vS1.setDuration(time);
        vS1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image1.setScaleX(v);
                image1.setScaleY(v);
            }
        });

        // 平移
        ValueAnimator vT1 = ValueAnimator.ofFloat(0, 30);
        vT1.setDuration(time);
        vT1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image1.setTranslationX(v);
            }
        });

        //透明度
        ValueAnimator vA1 = ValueAnimator.ofFloat(1, 0.88f);
        vA1.setDuration(time);
        vA1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image1.setAlpha(v);
            }
        });

        // 变化显示
        ValueAnimator vC1 = ValueAnimator.ofFloat(1, -1);
        vC1.setDuration(time);
        vC1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image1.setTranslationZ(v);
            }
        });

        // 缩放
        ValueAnimator vS2 = ValueAnimator.ofFloat(1, 1.1f);
        vS2.setDuration(time);
        vS2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image2.setScaleX(v);
                image2.setScaleY(v);
            }
        });

        // 平移
        ValueAnimator vT2 = ValueAnimator.ofFloat(0, -30);
        vT2.setDuration(time);
        vT2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image2.setTranslationX(v);
            }
        });

        //透明度
        ValueAnimator vA2 = ValueAnimator.ofFloat(0.88f, 1f);
        vA2.setDuration(time);
        vA2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image2.setAlpha(v);
            }
        });


        ValueAnimator vC2 = ValueAnimator.ofFloat(-1, 1);
        vC2.setDuration(time);
        vC2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image2.setTranslationZ(v);
            }
        });

        animatorSet.playTogether(vS1, vT1, vA1, vC1, vS2, vT2, vA2, vC2);
        mAnimatorSet.playSequentially(animatorStart, animatorSet);


        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (roll) {
                    // 执行其他动画

                } else {
                    roll = true;
                    mAnimatorSet.start();
                }
            }
        });
        mAnimatorSet.start();

    }


    @SuppressLint("Recycle")
    private void startAn2(ImageView image1, ImageView image2) {
        mAnimatorSet = new AnimatorSet();

        // 动画间隔
        ValueAnimator animatorStart = ValueAnimator.ofFloat(0f, 1f);
        animatorStart.setDuration(4000);

        AnimatorSet animatorSet = new AnimatorSet();

        // 缩放 x
        ValueAnimator vxS1 = ValueAnimator.ofInt(DimenUtils.dp2px(76), DimenUtils.dp2px(70));
        vxS1.setDuration(time);
        vxS1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) image1.getLayoutParams();
                layoutParams.width = v;
                image1.setLayoutParams(layoutParams);
            }
        });

        // 缩放 y
        ValueAnimator vyS1 = ValueAnimator.ofInt(DimenUtils.dp2px(108), DimenUtils.dp2px(100));
        vyS1.setDuration(time);
        vyS1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) image1.getLayoutParams();
                layoutParams.height = v;
                image1.setLayoutParams(layoutParams);
            }
        });

        // 平移
        ValueAnimator vT1 = ValueAnimator.ofFloat(movieXv1_0, movieXv1_1);
        vT1.setDuration(time);
        vT1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image1.setTranslationX(v);
            }
        });

        //透明度
        ValueAnimator vA1 = ValueAnimator.ofFloat(1, 0.88f);
        vA1.setDuration(time);
        vA1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image1.setAlpha(v);
            }
        });

        // 变化显示
        ValueAnimator vC1 = ValueAnimator.ofFloat(1, -1);
        vC1.setDuration(time);
        vC1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image1.setTranslationZ(v);
            }
        });


        // 缩放 x
        ValueAnimator vxS2 = ValueAnimator.ofInt(DimenUtils.dp2px(70), DimenUtils.dp2px(76));
        vxS2.setDuration(time);
        vxS2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) image2.getLayoutParams();
                layoutParams.width = v;
                image2.setLayoutParams(layoutParams);
            }
        });

        // 缩放 y
        ValueAnimator vyS2 = ValueAnimator.ofInt(DimenUtils.dp2px(100), DimenUtils.dp2px(108));
        vyS2.setDuration(time);
        vyS2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int v = (int) animation.getAnimatedValue();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) image2.getLayoutParams();
                layoutParams.height = v;
                image2.setLayoutParams(layoutParams);
            }
        });

        // 平移
        ValueAnimator vT2 = ValueAnimator.ofFloat(movieXv2_0, movieXv2_1);
        vT2.setDuration(time);
        vT2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image2.setTranslationX(v);
            }
        });


        //透明度
        ValueAnimator vA2 = ValueAnimator.ofFloat(0.88f, 1f);
        vA2.setDuration(time);
        vA2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image2.setAlpha(v);
            }
        });


        ValueAnimator vC2 = ValueAnimator.ofFloat(-1, 1);
        vC2.setDuration(time);
        vC2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                image2.setTranslationZ(v);
            }
        });

        animatorSet.playTogether(vxS1, vyS1, vT1, vA1, vC1, vxS2, vyS2, vT2, vA2, vC2);
        mAnimatorSet.playSequentially(animatorStart, animatorSet);


        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                roll = !roll;
                movieXv1_0 = roll ? -30 : 0;
                movieXv1_1 = roll ? 0 : 30;
                movieXv2_0 = roll ? 30 : 0;
                movieXv2_1 = roll ? 0 : -30;
                startAn2(roll ? binding.image2 : binding.image1, roll ? binding.image1 : binding.image2);
            }
        });
        mAnimatorSet.start();
    }



    @SuppressLint("Recycle")
    private void startAn3() {
        // startAn2(roll ? binding.image2 : binding.image1, roll ? binding.image1 : binding.image2);
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
        ValueAnimator vT1 = ValueAnimator.ofFloat(0, DimenUtils.dp2px(18f));
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

        animatorSet.playTogether(vxS1, vyS1, vT1, vA1, vC1, vxS2, vyS2, vT2, vA2, vC2);
        mAnimatorSet.playSequentially(animatorStart, animatorSet);


        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                roll = !roll;
                /*movieXv1_0 = roll ? -30 : 0;
                movieXv1_1 = roll ? 0 : 30;
                movieXv2_0 = roll ? 30 : 0;
                movieXv2_1 = roll ? 0 : -30;*/


                FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(DimenUtils.dp2px(106),DimenUtils.dp2px(152));
                params1.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
                FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(DimenUtils.dp2px(96),DimenUtils.dp2px(136));
                params2.gravity = Gravity.START | Gravity.CENTER_VERTICAL;
                binding.image2.setLayoutParams(roll ? params2 : params1);
                binding.image1.setLayoutParams(roll ? params1 : params2);


                Logger.debug("imageChange1 前  ==  " + imageChange1);
                Logger.debug("imageChange2 前  ==  " + imageChange2);
                imageChange1 = roll ? binding.image2 : binding.image1;
                imageChange2 = roll ? binding.image1 : binding.image2;
                Logger.debug("imageChange1 后  ==  " + imageChange1);
                Logger.debug("imageChange2 后  ==  " + imageChange2);
                Logger.debug("imageChange2 后  ==  " + DimenUtils.dp2px(6));
                Logger.debug("imageChange2 后  ==  " + DimenUtils.dp2px(12));

                mAnimatorSet.start();
            }
        });

    }




    @SuppressLint("Recycle")
    private void startAn4() {
        // 动画间隔
        ValueAnimator animatorStart = ValueAnimator.ofFloat(0f, 1f);
        animatorStart.setDuration(4000);

        AnimatorSet animatorSet = new AnimatorSet();

        // 缩放 x
        ValueAnimator vxS1 = ValueAnimator.ofFloat(1.0f,0.9f);
        vxS1.setDuration(time);
        vxS1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange3.setScaleX(v);
                imageChange3.setScaleY(v);
            }
        });

        // 平移
        ValueAnimator vT1 = ValueAnimator.ofFloat(movieXv1_0, movieXv1_1);
        vT1.setDuration(time);
        vT1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange3.setTranslationX(v);
            }
        });

        //透明度
        ValueAnimator vA1 = ValueAnimator.ofFloat(1, 0.88f);
        vA1.setDuration(time);
        vA1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange3.setAlpha(v);
            }
        });

        // 变化显示
        ValueAnimator vC1 = ValueAnimator.ofFloat(1, -1);
        vC1.setDuration(time);
        vC1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange3.setTranslationZ(v);
            }
        });


        // 缩放 x
        ValueAnimator vxS2 = ValueAnimator.ofFloat(0.9f,1f);
        vxS2.setDuration(time);
        vxS2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange4.setScaleX(v);
                imageChange4.setScaleY(v);
            }
        });

        // 平移
        ValueAnimator vT2 = ValueAnimator.ofFloat(movieXv2_0, movieXv2_1);
        vT2.setDuration(time);
        vT2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange4.setTranslationX(v);
            }
        });


        //透明度
        ValueAnimator vA2 = ValueAnimator.ofFloat(0.88f, 1f);
        vA2.setDuration(time);
        vA2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange4.setAlpha(v);
            }
        });


        ValueAnimator vC2 = ValueAnimator.ofFloat(-1, 1);
        vC2.setDuration(time);
        vC2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float v = (float) animation.getAnimatedValue();
                imageChange4.setTranslationZ(v);
            }
        });

        animatorSet.playTogether(vxS1, vT1, vA1, vC1, vxS2, vT2, vA2, vC2);
        mAnimatorSetTwo.playSequentially(animatorStart, animatorSet);


        mAnimatorSetTwo.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                roll = !roll;
                movieXv1_0 = roll ? -30 : 0;
                movieXv1_1 = roll ? 0 : 30;
                movieXv2_0 = roll ? 30 : 0;
                movieXv2_1 = roll ? 0 : -30;


                imageChange3 = roll ? binding.image4 : binding.image3;
                imageChange4 = roll ? binding.image3 : binding.image4;

                mAnimatorSetTwo.start();
            }
        });

    }



}
