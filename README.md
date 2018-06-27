# ZQLib
webview和js数据传输的使用

1、gradle文件中添加
compile 'com.github.zhang1078198775:ZQLib:1.2'

2、编写和js传输数据的接口文件，继承JavaSctiptInterface

3、打开webview
ActivityWebView.startWebView(context,接口全地址,html文件所在地址,"标题");
例子：ActivityWebView.startWebView(this,"com.cn.mobile.zqlib.MyJavaSctiptInterface","file:///android_asset/BridgeWebView/index.html","标题");
