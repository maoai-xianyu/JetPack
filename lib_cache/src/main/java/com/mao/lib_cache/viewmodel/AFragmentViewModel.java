package com.mao.lib_cache.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mao.lib_cache.R;
import com.mao.lib_cache.model.Story;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangkun
 * @time 2020/10/15 11:47 AM
 * @Description
 */
public class AFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Story>> listMutableLiveData;


    public MutableLiveData<List<Story>> getStories() {
        if (listMutableLiveData == null) {
            listMutableLiveData = new MutableLiveData<>();
            init();

        }

        return listMutableLiveData;
    }

    private void init() {

        ArrayList<Story> stories = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Story story = new Story("这是第 " + i + " 数据", R.drawable.movie_custom_actionbar_star_on);
            stories.add(story);
        }
        listMutableLiveData.setValue(stories);

    }
}
