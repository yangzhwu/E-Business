package com.zhengwuyang.latte_core.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.wang.avi.AVLoadingIndicatorView;
import com.zhengwuy.latte_core.R;
import com.zhengwuyang.latte_core.util.DimenUtil;

/**
 * Created by zhengwuy on 2018/12/20.
 * email: 13802885114@139.com
 * des:
 */
public class LoaderFragmentDialog extends AppCompatDialogFragment {
    private int mScaleSize = 8;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            AppCompatDialog dialog = new AppCompatDialog(getContext(), R.style.dialog);
            String loaderType = bundle.getString(LatteLoader.KEY_LOADER_TYPE);
            AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(loaderType, getContext());
            dialog.setContentView(avLoadingIndicatorView);

            int screenWidth = DimenUtil.getScreenWidth();
            int screenHeight = DimenUtil.getScreentHeight();
            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.width = screenWidth / mScaleSize;
                layoutParams.height = screenHeight / mScaleSize;
                layoutParams.gravity = Gravity.CENTER;
            }
            return dialog;
        }

        return super.onCreateDialog(savedInstanceState);
    }
}
