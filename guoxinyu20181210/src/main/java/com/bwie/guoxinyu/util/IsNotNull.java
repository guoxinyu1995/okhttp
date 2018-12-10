package com.bwie.guoxinyu.util;

import android.text.TextUtils;

public class IsNotNull {
    public static boolean isNot(String name,String password){
        return !TextUtils.isEmpty(name)&&!TextUtils.isEmpty(password);
    }
}
