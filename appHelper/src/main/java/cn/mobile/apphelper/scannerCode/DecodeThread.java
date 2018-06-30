package cn.mobile.apphelper.scannerCode;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangqi on 2018/6/30.
 */

final class DecodeThread extends Thread {

    private final CountDownLatch handlerInitLatch;
    ActivityScanerCode activity;
    private Handler handler;

    DecodeThread(ActivityScanerCode activity) {
        this.activity = activity;
        handlerInitLatch = new CountDownLatch(1);
    }

    Handler getHandler() {
        try {
            handlerInitLatch.await();
        } catch (InterruptedException ie) {
            // continue?
        }
        return handler;
    }

    @Override
    public void run() {
        Looper.prepare();
        handler = new DecodeHandler(activity);
        handlerInitLatch.countDown();
        Looper.loop();
    }

}
