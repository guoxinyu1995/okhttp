package com.example.week3_01.presenter;

import com.example.week3_01.model.ModelImpl;
import com.example.week3_01.model.MyCallBack;
import com.example.week3_01.view.Iview;
import com.example.week3_01.view.MainActivity;

import java.util.Map;

public class PresenterImpl implements Ipresenter {
    private Iview mIview;
    private ModelImpl model;

    public PresenterImpl(Iview iview) {
        mIview = iview;
        model = new ModelImpl();
    }

    @Override
    public void startRequest(String url, Map<String, String> params, Class clazz) {
        model.requestData(url, params, clazz, new MyCallBack() {
            @Override
            public void getData(Object o) {
                mIview.getRequest(o);
            }
        });
    }
    public void onDetach(){
        if(model!=null){
            model = null;
        }
        if(mIview!=null){
            mIview = null;
        }
    }
}
