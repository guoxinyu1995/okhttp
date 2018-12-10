package com.bwie.guoxinyu.api;

public class Apis {
    //post请求的接口
    public static final String URL_LOGIN_POST= "http://www.zhaoapi.cn/user/login";
    public static final String URL_REGISTER_POST = "http://www.zhaoapi.cn/user/reg";
    public static final String URL_CONTENT_POST = "http://www.zhaoapi.cn/home/getHome";
    //get请求的接口
    public static final String URL_LOGIN = "http://www.zhaoapi.cn/user/login?mobile=%s&password=%s";
    public static final String URL_REGISTER = "http://www.zhaoapi.cn/user/reg?mobile=%s&password=%s";
    public static final String URL_CONTENT = "http://www.zhaoapi.cn/home/getHome";
}
