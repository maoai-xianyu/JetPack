package com.mao.jetpack.ui.navigation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mao.jetpack.R;
import com.mao.jetpack.databinding.ActivityNavigationIndexBinding;

/**
 * @author zhangkun
 * @time 2020/10/9 8:43 PM
 * @Description
 */
public class NavigationIndexActivity extends AppCompatActivity {

    private ActivityNavigationIndexBinding navigationIndexBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationIndexBinding = DataBindingUtil.setContentView(this, R.layout.activity_navigation_index);
        initView();
    }

    private void initView() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host);
        NavController navController = NavHostFragment.findNavController(fragment);
        BottomNavigationView navBottom = navigationIndexBinding.navBottom;
        // 将BottomNavigationView 和 NaviGraph 关联起来
        NavigationUI.setupWithNavController(navBottom,navController);
    }


}
