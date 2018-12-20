package com.zhengwuyang.latte_core.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.zhengwuyang.latte_core.ConfigManager;

/**
 * Created by zhengwuy on 2018/12/20.
 * email: 13802885114@139.com
 * des:
 */
public class DimenUtil {
    public static int getScreenWidth() {
        Context context = ConfigManager.getInstance().getConfig(ConfigManager.ConfigType.APPLICATION_CONTEXT);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreentHeight() {
        Context context = ConfigManager.getInstance().getConfig(ConfigManager.ConfigType.APPLICATION_CONTEXT);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
