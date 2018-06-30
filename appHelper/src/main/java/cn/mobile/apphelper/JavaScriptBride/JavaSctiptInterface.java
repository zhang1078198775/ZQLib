package cn.mobile.apphelper.JavaScriptBride;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.WebView;

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

    public void invokeJavaScript(final String callback, final String json) {

        if(TextUtils.isEmpty(callback)) return;
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:" + callback + "(" + json + ")");
            }
        });
    }

}
