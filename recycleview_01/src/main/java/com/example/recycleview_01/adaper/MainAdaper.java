package com.example.recycleview_01.adaper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.recycleview_01.fragment.GridFragment;
import com.example.recycleview_01.fragment.LinearFragment;
import com.example.recycleview_01.fragment.StaggeredFragment;

public class MainAdaper extends FragmentPagerAdapter {
    String[] str = new String[]{
            "线性布局","表格布局","瀑布流"
    };
    public MainAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new LinearFragment();
            case 1:
                return new GridFragment();
            case 2:
                return new StaggeredFragment();
                default:
                    return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return str.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
