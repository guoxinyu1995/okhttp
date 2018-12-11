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

public class LinearAdaper extends RecyclerView.Adapter<LinearAdaper.ViewHolder> {
    private List<ContentBean.DataBean> mData;
    private Context mContext;

    public LinearAdaper(Context mContext) {
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
        notifyItemRangeChanged(i,mData.size());
    }
    @NonNull
    @Override
    //重写创建ViewHolder 把条目布局添加进来，返回viewHolder
    public LinearAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.liear_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //自定义内部类
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final ImageView image;
        public final TextView name;
        public final TextView createtime;
        public final ConstraintLayout constraintLayout;
        //写一个构造方法，找到所有的控件
        public ViewHolder(View view){
            super(view);
            image = view.findViewById(R.id.linear_image);
            name = view.findViewById(R.id.linear_name);
            createtime = view.findViewById(R.id.linear_createtime);
            constraintLayout = view.findViewById(R.id.lineras);
        }
    }
    // 绑定ViewHolder,写入数据
    @Override
    public void onBindViewHolder(@NonNull LinearAdaper.ViewHolder viewHolder, final int i) {
        ContentBean.DataBean dataBean = mData.get(i);
        viewHolder.name.setText(dataBean.getName());
        viewHolder.createtime.setText(dataBean.getCreatetime());
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


    private CallBack mCallBack;
    public void setOnClick(CallBack callBack){
        mCallBack = callBack;
    }
    //定义接口
    public interface CallBack{
        void onClick(int i);
        void onLongClick(int i);
    }

}
