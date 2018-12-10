package com.bwie.guoxinyu.model;

import com.bwie.guoxinyu.util.NetUtil;
import com.bwie.okhttputil.ICallBack;
import com.bwie.okhttputil.OkHttpUtil;

import java.util.Map;

public class ModelImpl implements Imodel {
    private MyCallBack myCallBack;
    //okhttp get请求
    @Override
    public void requestData(String url, String prames, Class clazz, final MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        /*NetUtil.getIntance().getRequest(url, clazz, new NetUtil.CallBack() {
            @Override
            public void onSuccess(Object o) {
                myCallBack.getData(o);
            }
        });*/
        //okhttp  get请求
        /*OkHttpUtil.getInstance().getAsynchronization(url, clazz, new ICallBack() {
            @Override
            public void success(Object o) {
                myCallBack.getData(o);
            }

            @Override
            public void failed(Exception e) {
                myCallBack.getData(e.getMessage());
            }
        });*/

    }
    //okhttp post请求
    @Override
    public void requestDataPost(String url, Map<String, String> params, Class clazz, final MyCallBack myCallBack) {
        OkHttpUtil.getInstance().postAsynchronization(url, params, clazz, new ICallBack() {
            @Override
            public void success(Object o) {
                    myCallBack.getData(o);
            }

            @Override
            public void failed(Exception e) {
                    myCallBack.getData(e.getMessage());
            }
        });
    }
}
