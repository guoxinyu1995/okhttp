package com.bwie.guoxinyu.model;

import java.util.Map;

public interface Imodel {
    void requestData(String url,String prames,Class clazz,MyCallBack myCallBack);
    //kohttp post请求
    void requestDataPost(String url, Map<String,String> params, Class clazz, MyCallBack myCallBack);
}
