package com.cn.mobile.zqlib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import cn.mobile.apphelper.JavaScriptBride.*;
import cn.mobile.apphelper.scannerCode.ActivityScanerCode;


public class MainActivity extends AppCompatActivity {

    private BridgeWebView mBdwebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    public void btnclick(View view){

//        Intent intent = new Intent(this, ActivityScanerCode.class);
//        startActivity(intent);
        ActivityWebView.startWebView(this,"com.cn.mobile.zqlib.MyJavaSctiptInterface","file:///android_asset/BridgeWebView/index.html","标题");
    }
}
