package com.bwie.guoxinyu.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bwie.guoxinyu.R;
import com.bwie.guoxinyu.adaper.ShowAdaper;

public class ShowActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ShowAdaper adaper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        //获取资源id
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.v_page);
        //创建适配器
        adaper = new ShowAdaper(getSupportFragmentManager());
        viewPager.setAdapter(adaper);
        tabLayout.setupWithViewPager(viewPager);
    }
}
