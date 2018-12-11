package com.example.recycleview_01.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.recycleview_01.R;
import com.example.recycleview_01.adaper.GridAdaper;
import com.example.recycleview_01.api.Apis;
import com.example.recycleview_01.bean.ContentBean;
import com.example.recycleview_01.custom.RecycleDecoration;
import com.example.recycleview_01.presenter.PresemterImpl;
import com.example.recycleview_01.view.Iview;

import java.util.HashMap;
import java.util.Map;

public class GridFragment extends Fragment implements Iview {

    private PresemterImpl presemter;
    private final int mSpanCount = 3;
    private RecyclerView recyclerView;
    private GridAdaper gridAdaper;
    private Button button;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.grid_fragment,container,false);
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
        recyclerView = view.findViewById(R.id.grid_recycle);
        button = view.findViewById(R.id.btn);
        //创建布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),mSpanCount);
        //设置方向
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //创建适配器
        gridAdaper = new GridAdaper(getActivity());
        //设置适配器
        recyclerView.setAdapter(gridAdaper);
        //设置分割线
        RecycleDecoration decoration = new RecycleDecoration(getActivity());
        recyclerView.addItemDecoration(decoration);
        //设置动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //点击
        gridAdaper.setonClick(new GridAdaper.CallBack() {
            @Override
            public void onClick(int i) {
                gridAdaper.delData(i);
            }

            @Override
            public void onLongClick(int i) {

            }
        });
        //添加
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentBean.DataBean dataBean = new ContentBean.DataBean();
                dataBean.setName("天猫");
                gridAdaper.addData(0,dataBean);
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
                gridAdaper.setmData(bean.getData());
            }
        }
    }
}
