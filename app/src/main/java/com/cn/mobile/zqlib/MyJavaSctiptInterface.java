package com.cn.mobile.zqlib;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangqi on 2018/6/26.
 */

public class MyJavaSctiptInterface {

    private WebView webView;
    private Activity mActivity;

    public MyJavaSctiptInterface(Activity mContext, WebView webView) {
        this.mActivity = mContext;
        this.webView = webView;
    }

    public void translateJsData(String[] strs) throws Exception {
        Toast.makeText(mActivity,"收到了来自js的数据"+strs[0],Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取酒店详情数据
     */
    public void getNativeData(String[] str){
        try {
            //解析js callback方法
            JSONObject mJson = new JSONObject(str[0]);
            String callback = mJson.optString("callback");//解析js回调方法


            JSONObject json = new JSONObject();
            json.put("userId", "w1083493");

            //调用js方法必须在主线程
            invokeJavaScript(callback, json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 统一管理所有android调用js方法
     *
     * @param callback js回调方法名
     * @param json     传递json数据
     */
    public void invokeJavaScript(final String callback, final String json) {

        if(TextUtils.isEmpty(callback)) return;
        //调用js方法必须在主线程
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:" + callback + "(" + json + ")");
            }
        });
    }

}
