package com.cn.mobile.apphelper.interfaces;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangqi on 2018/6/26.
 */

public class JavaSctiptInterface {

    public WebView webView;
    public Activity mActivity;

    public JavaSctiptInterface(Activity mContext, WebView webView) {
        this.mActivity = mContext;
        this.webView = webView;
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
