package com.mao.jetpack.ui.text;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.mao.jetpack.databinding.ActivityAnimatorTotalBinding;
import com.mao.jetpack.widget.rebound.CustomLayoutManager;

import java.util.ArrayList;
import java.util.List;

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


        List<String> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add("测试题 + " + i);
        }

        ReboundAdapter adapter = new ReboundAdapter();
        adapter.refreshData(list);
        binding.rv.setAdapter(adapter);


        ReboundAdapter adapter2 = new ReboundAdapter();
        adapter2.refreshData(list);

        binding.rv2.setAdapter(adapter2);


        ReboundAdapter adapter3 = new ReboundAdapter();
        adapter3.refreshData(list);

        binding.rv3.setAdapter(adapter3);


        ReboundAdapter adapter4 = new ReboundAdapter();
        adapter4.refreshData(list);
        binding.rv4.setAdapter(adapter4);

        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.rv4);


        ReboundAdapter adapter5 = new ReboundAdapter();
        adapter5.refreshData(list);
        binding.rv5.setLayoutManager(new CustomLayoutManager(this));
        binding.rv5.setAdapter(adapter5);


        ReboundAdapter adapter6 = new ReboundAdapter();
        adapter6.refreshData(list);
        binding.rv6.setAdapter(adapter6);


        binding.rv6.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //判断是当前layoutManager是否为LinearLayoutManager
                // 只有LinearLayoutManager才有查找第一个和最后一个可见view位置的方法
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    //获取最后一个可见view的位置
                    int lastItemPosition = linearManager.findLastVisibleItemPosition();
                    //获取第一个可见view的位置
                    int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                    Log.e("这个是什么子东西啊", lastItemPosition + "   " + firstItemPosition);

                    if (linearManager.findViewByPosition(linearManager.findFirstVisibleItemPosition()).getLeft() == 0
                            && linearManager.findFirstVisibleItemPosition() == 0) {
                        Log.e("滑动到了最边界", "最左边=============");

                    }

                }
            }
        });


        ReboundAdapter adapter7 = new ReboundAdapter();
        adapter7.refreshData(list);
        binding.rv7.setAdapter(adapter7);



        LinearLayoutManager layout = new LinearLayoutManager(this);
        layout.setOrientation(RecyclerView.HORIZONTAL);
        binding.rv8.setLayoutManager(layout);
        ReboundAdapter adapter8 = new ReboundAdapter();
        adapter8.refreshData(list);
        binding.rv8.setAdapter(adapter8);




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
                if (times[0] < 3) {
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
