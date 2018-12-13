package com.example.week3_01.model;

import java.util.Map;

public interface Imodel {
    void requestData(String url, Map<String,String> params,Class clazz,MyCallBack myCallBack);
}
