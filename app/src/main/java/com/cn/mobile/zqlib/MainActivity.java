package com.cn.mobile.zqlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cn.mobile.apphelper.activity.ActivityWebView;
import com.cn.mobile.apphelper.view.BridgeWebView;



public class MainActivity extends AppCompatActivity {

    private BridgeWebView mBdwebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnclick(View view){

        ActivityWebView.startWebView(this,"com.cn.mobile.zqlib.MyJavaSctiptInterface","file:///android_asset/BridgeWebView/index.html","标题");

    }
}
