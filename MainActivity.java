package com.laioffer.tinnews;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laioffer.tinnews.network.NewsApi;
import com.laioffer.tinnews.network.RetrofitClient;

// 控制nav的切换
public class MainActivity extends AppCompatActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 把 bottom 的 button 和 对应的页面 联系起来

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // 去 activity——main 里找这个 container
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        // 把control 给 底部的 navbar
        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navView, navController);

        // 连接 页面顶部
        // 没有 actionBar 就要删掉
        //NavigationUI.setupActionBarWithNavController(this, navController);

        NewsApi api = RetrofitClient.newInstance(this).create(NewsApi.class);
    }

    // handling the top left back button
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}
