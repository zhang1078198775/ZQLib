package cn.mobile.apphelper.photoselect;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.mobile.apphelper.R;
import cn.mobile.apphelper.common.utils.PhotoTool;
import cn.mobile.apphelper.common.view.MDialog;

/**
 * Created by zhangqi on 2018/6/29.
 */
public class ImageChooseDialog extends MDialog {

    private TextView mTvCamera;
    private TextView mTvFile;
    private TextView mTvCancel;

    public ImageChooseDialog(Activity context) {
        super(context);
        initView(context);
    }

    public ImageChooseDialog(Fragment fragment) {
        super(fragment.getContext());
        initView(fragment);
    }

    public ImageChooseDialog(Activity context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    public ImageChooseDialog(Fragment fragment, int themeResId) {
        super(fragment.getContext(), themeResId);
        initView(fragment);
    }

    public ImageChooseDialog(Activity context, float alpha, int gravity) {
        super(context, alpha, gravity);
        initView(context);
    }

    public ImageChooseDialog(Fragment fragment, float alpha, int gravity) {
        super(fragment.getContext(), alpha, gravity);
        initView(fragment);
    }

    public TextView getFromCameraView() {
        return mTvCamera;
    }

    public TextView getFromFileView() {
        return mTvFile;
    }

    public TextView getCancelView() {
        return mTvCancel;
    }


    private void initView(final Activity activity) {
        View dialogView = null;
        dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_picker_pictrue, null);

        mTvCamera = dialogView.findViewById(R.id.tv_camera);
        mTvFile = dialogView.findViewById(R.id.tv_file);
        mTvCancel = dialogView.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancel();
            }
        });
        mTvCamera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                PhotoTool.openCameraImage(activity);
                cancel();
            }
        });
        mTvFile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                PhotoTool.openLocalImage(activity);
                cancel();
            }
        });
        setContentView(dialogView);
        mLayoutParams.gravity = Gravity.BOTTOM;
    }

    private void initView(final Fragment fragment) {
        View dialogView = null;

        dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_picker_pictrue, null);
        mTvCamera =  dialogView.findViewById(R.id.tv_camera);
        mTvFile =  dialogView.findViewById(R.id.tv_file);
        mTvCancel =  dialogView.findViewById(R.id.tv_cancel);
        mTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                cancel();
            }
        });
        mTvCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //请求Camera权限
                PhotoTool.openCameraImage(fragment);
                cancel();
            }
        });
        mTvFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                PhotoTool.openLocalImage(fragment);
                cancel();
            }
        });

        setContentView(dialogView);
        mLayoutParams.gravity = Gravity.BOTTOM;
    }


}
