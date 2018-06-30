package cn.mobile.apphelper.scannerCode;

import android.graphics.Bitmap;

import com.google.zxing.Result;

/**
 * Created by zhangqi on 2018/6/30.
 */

public abstract class OnScanerListener {
    public void onSuccess(String type, Result result){

    }

    public void onFail(String type, String message){

    }

    public void generatePicture(Bitmap bitmap){

    }

    public void generatePicture(Result result){

    }
}
