package com.example.week3_01.adaper;

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
import com.example.week3_01.R;
import com.example.week3_01.bean.NewBean;

import java.util.ArrayList;
import java.util.List;

public class MainAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewBean.DataBean> mData;
    private Context mContext;
    private static final int TYPE_IMAGEONE = 0;
    private static final int TYPE_IMAGTWO= 1;
    private static final int TYPE_IMAGTHREE= 2;
    public MainAdaper(Context mContext) {
        this.mContext = mContext;
        mData = new ArrayList<>();
    }
    public void setmData(List<NewBean.DataBean> datas){
        mData.clear();
        if(datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }
    public void addmData(List<NewBean.DataBean> datas){
        if(datas!=null){
            mData.addAll(datas);
        }
        notifyDataSetChanged();
    }
    //删除
    public void delData(int i){
        mData.remove(i);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder = null;
        if(i == TYPE_IMAGEONE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_imageone,viewGroup,false);
            holder = new ViewHolderOne(view);
        }else if(i == TYPE_IMAGTWO){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_imagetwo,viewGroup,false);
            holder = new ViewHolderTwo(view);
        }else if(i == TYPE_IMAGTHREE){
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_imagethree,viewGroup,false);
            holder = new ViewHolderThree(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        int type = getItemViewType(i);
        switch (type){
            case TYPE_IMAGEONE:
                final ViewHolderOne viewHolderOne = (ViewHolderOne) viewHolder;
                viewHolderOne.title.setText(mData.get(i).getTitle());
                Glide.with(mContext).load(mData.get(i).getThumbnail_pic_s()).into(viewHolderOne.image_one);
                //图片点击监听

                    viewHolderOne.image_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(clickCallBack!=null) {
                                clickCallBack.setItemOnClick(viewHolderOne.image_one, i);
                            }
                        }
                    });

                //长按监听
                    viewHolderOne.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(longClickCallBack!=null){
                                longClickCallBack.setLongItemOnClick(i);
                            }
                            return true;
                        }
                    });

                break;
            case TYPE_IMAGTWO:
                final ViewHolderTwo viewHolderTwo = (ViewHolderTwo) viewHolder;
                viewHolderTwo.title.setText(mData.get(i).getTitle());
                Glide.with(mContext).load(mData.get(i).getThumbnail_pic_s()).into(viewHolderTwo.image_one);
                Glide.with(mContext).load(mData.get(i).getThumbnail_pic_s02()).into(viewHolderTwo.image_two);
                //图片点击监听

                    viewHolderTwo.image_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(clickCallBack!=null) {
                                clickCallBack.setItemOnClick(viewHolderTwo.image_one, i);
                            }
                        }
                    });
                    viewHolderTwo.image_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(clickCallBack!=null) {
                                clickCallBack.setItemOnClick(viewHolderTwo.image_two, i);
                            }
                        }
                    });
                //长按监听
                viewHolderTwo.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(longClickCallBack!=null){
                            longClickCallBack.setLongItemOnClick(i);
                        }
                        return true;
                    }
                });
                break;
            case TYPE_IMAGTHREE:
                final ViewHolderThree viewHolderThree = (ViewHolderThree) viewHolder;
                viewHolderThree.title.setText(mData.get(i).getTitle());
                Glide.with(mContext).load(mData.get(i).getThumbnail_pic_s()).into(viewHolderThree.image_one);
                Glide.with(mContext).load(mData.get(i).getThumbnail_pic_s02()).into(viewHolderThree.image_two);
                Glide.with(mContext).load(mData.get(i).getThumbnail_pic_s03()).into(viewHolderThree.image_three);
                //图片点击监听

                    viewHolderThree.image_one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(clickCallBack!=null) {
                                clickCallBack.setItemOnClick(viewHolderThree.image_one, i);
                            }
                        }
                    });
                    viewHolderThree.image_two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(clickCallBack!=null) {
                                clickCallBack.setItemOnClick(viewHolderThree.image_two, i);
                            }
                        }
                    });
                    viewHolderThree.image_three.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(clickCallBack!=null) {
                                clickCallBack.setItemOnClick(viewHolderThree.image_three, i);
                            }
                        }
                    });
                //长按监听
                viewHolderThree.constraintLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if(longClickCallBack!=null){
                            longClickCallBack.setLongItemOnClick(i);
                        }
                        return true;
                    }
                });
                break;
                default:
                    break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        String thumbnail_pic_s = mData.get(position).getThumbnail_pic_s();
        String thumbnail_pic_s02 = mData.get(position).getThumbnail_pic_s02();
        String thumbnail_pic_s03 = mData.get(position).getThumbnail_pic_s03();
        if(thumbnail_pic_s!=null && thumbnail_pic_s02==null && thumbnail_pic_s03 == null){
            return TYPE_IMAGEONE;
        }else if(thumbnail_pic_s!=null && thumbnail_pic_s02!=null && thumbnail_pic_s03 == null){
            return TYPE_IMAGTWO;
        }else if(thumbnail_pic_s!=null && thumbnail_pic_s02!=null && thumbnail_pic_s03 != null){
            return TYPE_IMAGTHREE;
        }
            return 0;

    }

    /**
     * 第一个条目
     * */
    public class ViewHolderOne extends RecyclerView.ViewHolder{
        private final TextView title;
        private final ImageView image_one;
        private final ConstraintLayout constraintLayout;
        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image_one = itemView.findViewById(R.id.image_one);
            constraintLayout = itemView.findViewById(R.id.con);
        }
    }
    /**
     * 第二个条目
     * */
    public class ViewHolderTwo extends RecyclerView.ViewHolder{
        private final TextView title;
        private final ImageView image_one;
        private final ImageView image_two;
        private final ConstraintLayout constraintLayout;
        public ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image_one = itemView.findViewById(R.id.image_one);
            image_two = itemView.findViewById(R.id.image_two);
            constraintLayout = itemView.findViewById(R.id.con);
        }
    }
    /**
     * 第三个条目
     * */
    public class ViewHolderThree extends RecyclerView.ViewHolder{
        private final TextView title;
        private final ImageView image_one;
        private final ImageView image_two;
        private final ImageView image_three;
        private final ConstraintLayout constraintLayout;
        public ViewHolderThree(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image_one = itemView.findViewById(R.id.image_one);
            image_two = itemView.findViewById(R.id.image_two);
            image_three = itemView.findViewById(R.id.image_three);
            constraintLayout = itemView.findViewById(R.id.con);
        }
    }
    //定义接口 用于点击
    private onClickCallBack clickCallBack;
    public interface onClickCallBack{
        void setItemOnClick(View item,int i);
    }
    public void setItemOnClickListen(onClickCallBack clickCallBack){
        this.clickCallBack = clickCallBack;
    }
    //定义接口 用于长按
    private onLongClickCallBack longClickCallBack;
    public interface onLongClickCallBack{
        void setLongItemOnClick(int i);
    }
    public void setLongItemOnClickListen(onLongClickCallBack longClickCallBack){
        this.longClickCallBack = longClickCallBack;
    }
}
