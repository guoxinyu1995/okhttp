package com.bwie.guoxinyu.presenter;

import java.util.Map;

public interface Ipresemter {
    void startRequest(String url,String prames,Class clazz);
    void startRequestPost(String url, Map<String,String> params, Class clazz);
}
