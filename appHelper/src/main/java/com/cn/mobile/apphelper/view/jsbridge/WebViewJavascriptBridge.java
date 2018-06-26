package com.cn.mobile.apphelper.view.jsbridge;

public interface WebViewJavascriptBridge {

	public void send(String data);

	public void send(String data, CallBackFunction responseCallback);

}
