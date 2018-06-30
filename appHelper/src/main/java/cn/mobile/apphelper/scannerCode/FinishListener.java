package cn.mobile.apphelper.scannerCode;

import android.app.Activity;
import android.content.DialogInterface;

/**
 * Created by zhangqi on 2018/6/30.
 */

public final class FinishListener  implements DialogInterface.OnClickListener, DialogInterface.OnCancelListener, Runnable {

    private final Activity activityToFinish;

    public FinishListener(Activity activityToFinish) {
        this.activityToFinish = activityToFinish;
    }

    public void onCancel(DialogInterface dialogInterface) {
        run();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        run();
    }

    public void run() {
        activityToFinish.finish();
    }
}
