package com.bwie.guoxinyu.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.guoxinyu.api.Apis;
import com.bwie.guoxinyu.R;
import com.bwie.guoxinyu.bean.BannersBean;
import com.bwie.guoxinyu.presenter.PresenderImpl;
import com.bwie.guoxinyu.view.Iview;
import com.bwie.guoxinyu.view.SweepActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoaderInterface;

import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements Iview {
    //private String url = "http://www.zhaoapi.cn/home/getHome";
    private ImageView sweep;
    private Banner banner;
    private PresenderImpl presender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presender = new PresenderImpl(this);
        //获取资源id
        sweep = view.findViewById(R.id.sweep);
        banner = view.findViewById(R.id.banner);
        sweep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sweepCode();
            }
        });
        initData();
    }
        //扫描二维码
        private void sweepCode() {
            //判断是否是6.0以上
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                //添加相机权限
                if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.CAMERA)!=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},100);
                }else{
                    Intent intent = new Intent(getActivity(),SweepActivity.class);
                    startActivity(intent);
                }
                //添加网络权限
                if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.INTERNET)!=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.INTERNET},200);
                }else{
                    Intent intent = new Intent(getActivity(),SweepActivity.class);
                    startActivity(intent);
                }
            }else{
                Intent intent = new Intent(getActivity(),SweepActivity.class);
                startActivity(intent);
            }
        }

    private void initData() {
        //okhttp get请求的方法
        //presender.startRequest(Apis.URL_CONTENT,null,BannersBean.class);
        //okhttp post请求的方法
        Map<String,String> params = new HashMap<>();
        presender.startRequestPost(Apis.URL_CONTENT_POST,params,BannersBean.class);
    }

    @Override
    public void getRequest(Object o) {
        if(o instanceof BannersBean){
            BannersBean bean = (BannersBean) o;
            banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
            banner.setImageLoader(new ImageLoaderInterface<ImageView>() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannersBean.DataBean.BannerBean dataBean = (BannersBean.DataBean.BannerBean) path;
                    com.nostra13.universalimageloader.core.ImageLoader.getInstance().displayImage(dataBean.hasImage(),imageView);
                }

                @Override
                public ImageView createImageView(Context context) {
                    ImageView imageView = new ImageView(context);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    return imageView;
                }
            });
            banner.setImages(bean.getData().getBanner());
            banner.start();
        }
    }
    //动态添加网络权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(getActivity(),SweepActivity.class);
            startActivity(intent);
        }else if(requestCode == 200 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(getActivity(),SweepActivity.class);
            startActivity(intent);
        }
    }
}
