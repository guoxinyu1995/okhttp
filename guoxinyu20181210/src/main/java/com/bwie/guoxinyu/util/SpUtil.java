package com.bwie.guoxinyu.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtil {
    private static final String SP_NAME = "OKHTTP_TEST";
    /**
     * 保存
     * @param context 上下文
     * @param key key值
     * @param object value值
     */
    public static void save(Context context,String key,Object object){
        SharedPreferences preferences = context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE);
        if(object instanceof String){
            preferences.edit().putString(key, (String) object).commit();
        }else if(object instanceof Boolean){
            preferences.edit().putBoolean(key, (Boolean) object).commit();
        }

    }
    /**
     * 获取字符串
     * @param context
     * @param key
     * @param defultObject 默认值
     * @return
     */
    public static Object getString(Context context,String key,Object defultObject){
        if(defultObject instanceof String){
            return context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE).getString(key, (String) defultObject);
        }else if(defultObject instanceof Boolean){
            return context.getSharedPreferences(SP_NAME,Context.MODE_PRIVATE).getBoolean(key, (Boolean) defultObject);
        }
        return null;
    }
}
