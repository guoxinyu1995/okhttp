package com.bwie.guoxinyu.presenter;

import com.bwie.guoxinyu.model.ModelImpl;
import com.bwie.guoxinyu.model.MyCallBack;
import com.bwie.guoxinyu.view.Iview;
import com.bwie.guoxinyu.view.MainActivity;

import java.util.Map;

public class PresenderImpl implements Ipresemter {
    private Iview mIview;
    private final ModelImpl model;

    public PresenderImpl(Iview iview) {
        mIview = iview;
        model = new ModelImpl();
    }

    @Override
    public void startRequest(String url, String prames, Class clazz) {
       /* model.requestData(url, null, clazz, new MyCallBack() {
            @Override
            public void getData(Object data) {
                mIview.getRequest(data);
            }
        });*/
    }
    //okhttp post请求
    @Override
    public void startRequestPost(String url, Map<String, String> params, Class clazz) {
            model.requestDataPost(url, params, clazz, new MyCallBack() {
                @Override
                public void getData(Object data) {
                    mIview.getRequest(data);
                }
            });
    }


}
