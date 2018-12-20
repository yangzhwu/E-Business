package com.zhengwuyang.latte_core.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by zhengwuy on 2018/12/20.
 * email: 13802885114@139.com
 * des:
 */
public class LatteLoader {
    static final String KEY_LOADER_TYPE = "loader_type";

    public static AppCompatDialogFragment createLoadingDialog(FragmentActivity supportActivity, LoaderType loaderType) {
        LoaderFragmentDialog loaderFragmentDialog = new LoaderFragmentDialog();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_LOADER_TYPE, loaderType.name());
        loaderFragmentDialog.setArguments(bundle);
        return loaderFragmentDialog;
    }
}
