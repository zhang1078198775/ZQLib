package com.cn.mobile.zqlib;

import android.app.Activity;
import android.webkit.WebView;
import android.widget.Toast;

import cn.mobile.apphelper.JavaScriptBride.*;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangqi on 2018/6/26.
 */
public class MyJavaSctiptInterface extends JavaSctiptInterface{


    public MyJavaSctiptInterface(Activity mContext, WebView webView) {
        super(mContext, webView);
    }

    public void translateJsData(String[] strs) throws Exception {
        Toast.makeText(mActivity,"收到了来自js的数据"+strs[0],Toast.LENGTH_SHORT).show();
    }

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



}
