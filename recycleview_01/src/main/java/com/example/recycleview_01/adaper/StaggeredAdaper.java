package com.example.recycleview_01.adaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.recycleview_01.R;
import com.example.recycleview_01.bean.ContentBean;
import com.example.recycleview_01.bean.ImageBean;

import java.util.ArrayList;
import java.util.List;

public class StaggeredAdaper extends RecyclerView.Adapter<StaggeredAdaper.ViewHolder> {
    private List<ContentBean.DataBean> mList;
    private Context mContext;

    public StaggeredAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }
    public void setmList(List<ContentBean.DataBean> lists){
        mList.clear();
        if(lists!=null){
            mList.addAll(lists);
        }
        notifyDataSetChanged();
    }
    //删除
    public void delData(int i){
        mList.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeRemoved(i,mList.size());
    }
    @NonNull
    @Override
    public StaggeredAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.staggerd_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaggeredAdaper.ViewHolder viewHolder, final int i) {
        ContentBean.DataBean dataBean = mList.get(i);
        Glide.with(mContext).load(dataBean.getIcon()).into(viewHolder.iamge);
        //点击监听
        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCallBack!=null){
                    mCallBack.onClick(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView iamge;
        public final ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iamge = itemView.findViewById(R.id.staggerd_image);
            constraintLayout = itemView.findViewById(R.id.staggered);
        }
    }
    //点击事件的接口回调
    private GridAdaper.CallBack mCallBack;
    public void setonClick(GridAdaper.CallBack callBack){
        mCallBack = callBack;
    }
    public interface CallBack{
        void onClick(int i);
        void onLongClick(int i);
    }
}
