package com.example.recycleview_01.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.recycleview_01.R;
import com.example.recycleview_01.adaper.GridAdaper;
import com.example.recycleview_01.adaper.StaggeredAdaper;
import com.example.recycleview_01.api.Apis;
import com.example.recycleview_01.bean.ContentBean;
import com.example.recycleview_01.bean.ImageBean;
import com.example.recycleview_01.custom.RecycleDecoration;
import com.example.recycleview_01.presenter.PresemterImpl;
import com.example.recycleview_01.view.Iview;

import java.util.HashMap;
import java.util.Map;

public class StaggeredFragment extends Fragment implements Iview {

    private PresemterImpl presemter;
    private RecyclerView recyclerView;
    private final int mSpanCount = 3;
    private StaggeredAdaper adaper;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.staggered_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presemter = new PresemterImpl(this);
        initView(view);
        initData();
    }

    private void initData() {
        Map<String,String> params = new HashMap<>();
        presemter.startRequest(Apis.URL_CONTENT,params,ContentBean.class);
    }

    private void initView(View view) {
        //获取资源id
        recyclerView = view.findViewById(R.id.staggered_recycle);
        //创建布局管理器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(mSpanCount,StaggeredGridLayoutManager.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //创建适配器
        adaper = new StaggeredAdaper(getActivity());
        recyclerView.setAdapter(adaper);
        //设置分割线
        RecycleDecoration decoration = new RecycleDecoration(getActivity());
        recyclerView.addItemDecoration(decoration);
        //设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //点击
        adaper.setonClick(new GridAdaper.CallBack() {
            @Override
            public void onClick(int i) {
                adaper.delData(i);
            }

            @Override
            public void onLongClick(int i) {

            }
        });
    }

    @Override
    public void getRequest(Object o) {
        if(o instanceof ContentBean){
           ContentBean bean = (ContentBean) o;
            if(bean==null || !bean.isSuccess()){
                Toast.makeText(getActivity(),bean.getMsg(),Toast.LENGTH_SHORT).show();
            }else{
                adaper.setmList(bean.getData());
            }
        }
    }
}
