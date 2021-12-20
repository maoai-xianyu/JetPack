package com.mao.jetpack.bannerloop;

import androidx.viewpager.widget.ViewPager;

public interface ViewPagerIndicator extends ViewPager.OnPageChangeListener {

    void setViewPager(ViewPager viewPager);

    void notifyDataSetChanged();
}
