package com.bwie.guoxinyu.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.bwie.guoxinyu.R;
import com.bwie.guoxinyu.adaper.ShowAdaper;
import com.google.zxing.qrcode.encoder.QRCode;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class SweepActivity extends AppCompatActivity implements QRCodeView.Delegate {


    private ZXingView xingView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sweep);
        xingView = findViewById(R.id.sweep);
        xingView.setDelegate(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        xingView.startCamera();
        xingView.startSpotAndShowRect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        xingView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        xingView.onDestroy();
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
