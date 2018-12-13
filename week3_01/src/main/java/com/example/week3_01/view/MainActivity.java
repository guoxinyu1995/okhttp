package com.example.week3_01.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.week3_01.R;
import com.example.week3_01.adaper.MainAdaper;
import com.example.week3_01.api.Apis;
import com.example.week3_01.bean.NewBean;
import com.example.week3_01.presenter.PresenterImpl;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Iview{

    private PresenterImpl presenter;
    private ImageView imageView;
    private TextView textView;
    private Button button;
    private XRecyclerView xRecyclerView;
    private MainAdaper adaper;
    private int mPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new PresenterImpl(this);
        initView();
    }

    private void initData() {
        presenter.startRequest(String.format(Apis.URL_NEWS,mPage),null,NewBean.class);
    }

    private void initView() {
        mPage = 1;
        //获取资源id
        imageView = findViewById(R.id.image);
        textView = findViewById(R.id.text_name);
        button = findViewById(R.id.btn);
        xRecyclerView = findViewById(R.id.x_recycle);
        //创建管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        xRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置分割线
        DividerItemDecoration decoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        xRecyclerView.addItemDecoration(decoration);
        //创建适配器
        adaper = new MainAdaper(this);
        //设置适配器
        xRecyclerView.setAdapter(adaper);
        //设置是否支持加载
        xRecyclerView.setPullRefreshEnabled(true);
        //设置支持刷新
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                initData();

            }

            @Override
            public void onLoadMore() {
                initData();

            }
        });
        initData();
        //点击
        adaper.setItemOnClickListen(new MainAdaper.onClickCallBack() {
            @Override
            public void setItemOnClick(View item, int i) {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(item,"alpha",1f,0f,1f);
                alpha.setDuration(3000);
                alpha.start();
            }
        });
        //长按
        adaper.setLongItemOnClickListen(new MainAdaper.onLongClickCallBack() {
            @Override
            public void setLongItemOnClick(final int i) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("删除......");
                builder.setMessage("确认删除么？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adaper.delData(i);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
        //点击登录qq
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UMShareAPI umShareAPI = UMShareAPI.get(MainActivity.this);
                umShareAPI.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        Log.i("gxy","UMAuthListener onComplete");
                        String screen_name = map.get("screen_name");
                        String profile_image_url = map.get("profile_image_url");
                        textView.setText(screen_name);
                        Glide.with(MainActivity.this).load(profile_image_url).into(imageView);
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
            }
        });
        //点击头像设置动画
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator scaleX = ObjectAnimator.ofFloat(imageView,"scaleX",1f,2f,1f);
                ObjectAnimator scaleY = ObjectAnimator.ofFloat(imageView,"scaleY",1f,2f,1f);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.setDuration(3000);
                animatorSet.playTogether(scaleX,scaleY);
                animatorSet.start();
            }
        });
    }

    @Override
    public void getRequest(Object o) {
        if(o instanceof NewBean){
            NewBean bean = (NewBean) o;
            if(bean == null || !bean.isSuccess()){
                Toast.makeText(MainActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
            }else{
                if(mPage == 1){
                    adaper.setmData(bean.getData());
                }else{
                    adaper.addmData(bean.getData());
                }
                mPage++;
                xRecyclerView.refreshComplete();
                xRecyclerView.loadMoreComplete();
            }


        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
