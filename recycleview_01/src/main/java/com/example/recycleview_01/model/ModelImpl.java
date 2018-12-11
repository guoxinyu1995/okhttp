package com.example.recycleview_01.model;

import com.example.recycleview_01.utils.ICallBack;
import com.example.recycleview_01.utils.OkHttpUtils;

import java.util.Map;

public class ModelImpl implements Imodel {
    private MyCallBack myCallBack;
    @Override
    public void requestData(String url, Map<String, String> params, Class clazz, final MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        OkHttpUtils.getInstance().postAsynchronization(url, params, clazz, new ICallBack() {
            @Override
            public void success(Object obj) {
                myCallBack.getData(obj);
            }

            @Override
            public void failed(Exception e) {
                myCallBack.getData(e.getMessage());
            }
        });
    }
}
