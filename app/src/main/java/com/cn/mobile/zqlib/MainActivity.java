package com.cn.mobile.zqlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.cn.mobile.apphelper.view.BridgeWebView;


public class MainActivity extends AppCompatActivity {

    private BridgeWebView mBdwebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sajdlsajd

        mBdwebview = (BridgeWebView) findViewById(R.id.bdwebview);//初始化BridgeWebView
        mBdwebview.loadUrl("file:///android_asset/BridgeWebView/index.html");
        mBdwebview.addBridgeInterface(new MyJavaSctiptInterface(this,mBdwebview));//注册桥梁类，该类负责H5和android通信


        mBdwebview.setWebViewClient(new WebViewClient());
        mBdwebview.setWebChromeClient(new WebChromeClient());
    }
}
