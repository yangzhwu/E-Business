package com.zhengwuy.ebapplication;

import android.app.Application;

import com.joanzapata.android.iconify.fonts.FontAwesomeModule;
import com.zhengwuy.latte_ec.FontEcModule;
import com.zhengwuyang.latte_core.ConfigManager;

/**
 * Created by zhengwuy on 2018/12/17.
 * email: 13802885114@139.com
 * des:
 */
public class MyApplication  extends Application {
    @Override
    public void onCreate() {
        ConfigManager.init(this).withApiHost("http://127.0.0.1").withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule()).config();
        super.onCreate();
    }
}
