package cn.mobile.apphelper.JavaScriptBride;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import java.lang.reflect.Constructor;

import cn.mobile.apphelper.R;


/**
 * Created by zhangqi on 2018/6/5.
 */
public class ActivityWebView extends Activity {

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    ProgressBar pbWebBase;
    BridgeWebView webBase;
    ImageView ivFinish;
    TextAutoZoom mRxTextAutoZoom;
    LinearLayout llIncludeTitle;
    private String webPath = "";
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initView();// 初始化控件 - FindViewById之类的操作
        initData();// 初始化控件的数据及监听事件
    }



    public static void startWebView(Context mContext, String sctiptInterface,String url,String showTitle){

        Intent intent=new Intent(mContext, ActivityWebView.class);
        intent.putExtra("scriptInterface", sctiptInterface);
        intent.putExtra("showTitle",showTitle);
        intent.putExtra("url",url);
        mContext.startActivity(intent);
    }


    private void initView() {
        mRxTextAutoZoom = (TextAutoZoom) findViewById(R.id.afet_tv_title);
        llIncludeTitle = (LinearLayout) findViewById(R.id.ll_include_title);
        pbWebBase = (ProgressBar) findViewById(R.id.pb_web_base);
        webBase = (BridgeWebView) findViewById(R.id.web_base);
        ivFinish = (ImageView) findViewById(R.id.iv_finish);
        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webBase.canGoBack()) {
                    webBase.goBack();
                } else {
                finish();
                }
            }
        });

        initAutoFitEditText();
    }

    public void initAutoFitEditText() {

        mRxTextAutoZoom.clearFocus();
        mRxTextAutoZoom.setEnabled(false);
        mRxTextAutoZoom.setFocusableInTouchMode(false);
        mRxTextAutoZoom.setFocusable(false);
        mRxTextAutoZoom.setEnableSizeCache(false);
        //might cause crash on some devices
        mRxTextAutoZoom.setMovementMethod(null);
        // can be added after layout inflation;
        mRxTextAutoZoom.setMaxHeight(dp2px(55f));
        //don't forget to add min text size programmatically
        mRxTextAutoZoom.setMinTextSize(37f);

        TextAutoZoom.setNormalization(this, llIncludeTitle, mRxTextAutoZoom);

        KeyboardTool.hideSoftInput(this);
    }

    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void initData() {
        pbWebBase.setMax(100);//设置加载进度最大值
        webPath = getIntent().getStringExtra("url");
//        webPath = RxConstants.URL_BAIDU_SEARCH;//加载的URL
        if (webPath.equals("")) {
            webPath = "http://www.baidu.com";
        }
        WebSettings webSettings = webBase.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//加载缓存否则网络
        }

        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);//图片自动缩放 打开
        } else {
            webSettings.setLoadsImagesAutomatically(false);//图片自动缩放 关闭
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webBase.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//软件解码
        }
        webBase.setLayerType(View.LAYER_TYPE_HARDWARE, null);//硬件解码


        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
//        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setSupportZoom(true);// 设置可以支持缩放
        webSettings.setBuiltInZoomControls(true);// 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false

        webSettings.setDisplayZoomControls(false);//隐藏缩放工具
        webSettings.setUseWideViewPort(true);// 扩大比例的缩放

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setDatabaseEnabled(true);//
        webSettings.setSavePassword(true);//保存密码
        webSettings.setDomStorageEnabled(true);//是否开启本地DOM存储  鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android 默认是关闭该功能的。
        webBase.setSaveEnabled(true);
        webBase.setKeepScreenOn(true);


        // 设置setWebChromeClient对象
        webBase.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mRxTextAutoZoom.setText(title);
                if(getIntent().getStringExtra("showTitle")!=null){
                    mRxTextAutoZoom.setText(getIntent().getStringExtra("showTitle"));
                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                pbWebBase.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        //设置此方法可在WebView中打开链接，反之用浏览器打开
        webBase.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (!webBase.getSettings().getLoadsImagesAutomatically()) {
                    webBase.getSettings().setLoadsImagesAutomatically(true);
                }
                pbWebBase.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pbWebBase.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                }
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        webBase.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String paramAnonymousString1, String paramAnonymousString2, String paramAnonymousString3, String paramAnonymousString4, long paramAnonymousLong) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(Uri.parse(paramAnonymousString1));
                startActivity(intent);
            }
        });
        webBase.loadUrl(webPath);
        String sctiptInterface= getIntent().getStringExtra("scriptInterface");

        try {

            Class clazz = ActivityWebView.this.getClassLoader().loadClass(sctiptInterface);
            Constructor constructor = clazz.getConstructor(Activity.class,WebView.class);
            Object obj = constructor.newInstance(this,webBase);
            webBase.addBridgeInterface(obj);//注册桥梁类，该类负责H5和android通信
        }catch (Exception e){
            e.printStackTrace();

        }




    //    webBase.loadUrl(webPath);
//        Log.v("帮助类完整连接", webPath);
//        webBase.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,webBase.getHeight()));
    }

    @Override
    protected void onSaveInstanceState(Bundle paramBundle) {
        super.onSaveInstanceState(paramBundle);
        paramBundle.putString("url", webBase.getUrl());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        try {
            super.onConfigurationChanged(newConfig);
            if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Log.v("Himi", "onConfigurationChanged_ORIENTATION_LANDSCAPE");
            } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                Log.v("Himi", "onConfigurationChanged_ORIENTATION_PORTRAIT");
            }
        } catch (Exception ex) {
        }
    }


}
