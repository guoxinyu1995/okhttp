package com.bwie.guoxinyu.adaper;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bwie.guoxinyu.fragment.ContentFragment;
import com.bwie.guoxinyu.fragment.HomeFragment;

public class ShowAdaper extends FragmentPagerAdapter {
    String[] str = new String[]{
            "首页","分类","旬Me","购物车","我的"
    };
    public ShowAdaper(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new HomeFragment();
                default:
                    return new ContentFragment();
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
