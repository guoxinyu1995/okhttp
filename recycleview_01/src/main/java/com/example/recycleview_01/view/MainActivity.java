package com.example.recycleview_01.view;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.recycleview_01.R;
import com.example.recycleview_01.adaper.MainAdaper;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainAdaper mainAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取资源id
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.v_pager);
        //创建适配器
        mainAdaper = new MainAdaper(getSupportFragmentManager());
        viewPager.setAdapter(mainAdaper);
        tabLayout.setupWithViewPager(viewPager);
    }
}
