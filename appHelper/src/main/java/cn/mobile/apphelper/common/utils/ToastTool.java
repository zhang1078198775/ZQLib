package cn.mobile.apphelper.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhangqi on 2018/6/29.
 */

public class ToastTool {
    public static void showShortToast(Context mContext,String msg) {
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }
}
