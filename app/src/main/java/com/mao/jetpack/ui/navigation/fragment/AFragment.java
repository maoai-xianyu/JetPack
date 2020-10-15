package com.mao.jetpack.ui.navigation.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.mao.jetpack.R;
import com.mao.jetpack.databinding.FragmentABinding;
import com.mao.lib_cache.adapter.StoryAdapter;
import com.mao.lib_cache.viewmodel.AFragmentViewModel;
import com.mao.lib_common.utils.Logger;

/**
 * @author zhangkun
 * @time 2020/10/9 8:56 PM
 * @Description
 */
public class AFragment extends Fragment {

    private ImageView viewById;
    private FragmentABinding fragmentABinding;
    private AFragmentViewModel aFragmentViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.debug("AFragment  onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentABinding = DataBindingUtil.inflate(inflater, R.layout.fragment_a, container, false);
        // 获取 recycler
        fragmentABinding.recycler.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));

        aFragmentViewModel = new AFragmentViewModel();
        StoryAdapter storyAdapter = new StoryAdapter(aFragmentViewModel.getStories().getValue());
        fragmentABinding.recycler.setAdapter(storyAdapter);

        initView();
        return fragmentABinding.getRoot();
    }

    protected void initView() {

        viewById = fragmentABinding.iv;
        viewById.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 3.7 使用 SimpleTarget 会让 placeholder 失效
               /* Glide.with(getActivity())
                        //.load("https://avatars0.githubusercontent.com/u/17868022?s=60&v=4")
                        .load(R.drawable.movie_custom_actionbar_star_on)
                        .placeholder(R.drawable.icon_movie_detail_award_cup)
                        .error(R.mipmap.ic_launcher)
                        .into(new SimpleTarget<GlideDrawable>() {
                            @Override
                            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                                viewById.setImageDrawable(resource);
                            }
                        });*/

                RequestOptions requestOptions = new RequestOptions();
                requestOptions.placeholder(R.drawable.icon_movie_detail_award_cup);
                requestOptions.error(R.mipmap.ic_launcher);

                /*Glide.with(getActivity())
                        .load(R.drawable.movie_custom_actionbar_star_on)
                        .apply(requestOptions)
                        .into(new ImageViewTarget<Drawable>(viewById) {
                            @Override
                            protected void setResource(@Nullable Drawable resource) {
                                viewById.setImageDrawable(resource);
                            }
                        });*/

                /*Glide.with(getActivity())
                        .load(R.drawable.movie_custom_actionbar_star_on)
                        .apply(requestOptions)
                        .into(new DrawableImageViewTarget(viewById));*/


                // 没有显示占位图片
                Glide.with(getActivity())
                        .load(R.drawable.movie_custom_actionbar_star_on)
                        .apply(requestOptions)
                        .into(new CustomViewTarget(viewById){
                            @Override
                            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                                viewById.setImageDrawable(errorDrawable);
                            }

                            @Override
                            public void onResourceReady(@NonNull Object resource, @Nullable Transition transition) {
                                viewById.setImageDrawable((Drawable) resource);
                            }

                            @Override
                            protected void onResourceCleared(@Nullable Drawable placeholder) {
                                viewById.setImageDrawable(placeholder);
                            }
                        });
            }
        }, 2000);


    }
}
