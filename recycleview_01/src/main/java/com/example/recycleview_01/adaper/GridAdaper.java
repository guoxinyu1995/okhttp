package com.example.recycleview_01.adaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.recycleview_01.R;
import com.example.recycleview_01.bean.ContentBean;

import java.util.ArrayList;
import java.util.List;

public class GridAdaper extends RecyclerView.Adapter<GridAdaper.ViewHolder> {
    private List<ContentBean.DataBean> mData;
    private Context mContext;

    public GridAdaper(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }
    public void setmData(List<ContentBean.DataBean> datas){
        mData.clear();
        if(datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }
    //删除
    public void delData(int i){
        mData.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeRemoved(i,mData.size());
    }
    /**
     * 增加数据，传入添加的位置和数据
     */
    public void addData(int position, ContentBean.DataBean datas) {
        mData.add(position, datas);
        //必须使用notifyItemInserted 才能加载添加动画
        notifyItemInserted(position);
    }
    @NonNull
    @Override
    public GridAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grid_item,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdaper.ViewHolder viewHolder, final int i) {
        ContentBean.DataBean dataBean = mData.get(i);
        viewHolder.name.setText(dataBean.getName());
        Glide.with(mContext).load(dataBean.getIcon()).into(viewHolder.image);
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
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView image;
        public final TextView name;
        public final ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.grid_image);
            name = itemView.findViewById(R.id.grid_name);
            constraintLayout = itemView.findViewById(R.id.grid);
        }
    }
    //点击事件的接口回调
    private CallBack mCallBack;
    public void setonClick(CallBack callBack){
        mCallBack = callBack;
    }
    public interface CallBack{
        void onClick(int i);
        void onLongClick(int i);
    }
}
