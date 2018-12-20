package com.zhengwuyang.latte_core.ui;

import android.content.Context;
import android.text.TextUtils;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.HashMap;

/**
 * Created by zhengwuy on 2018/12/19.
 * email: 13802885114@139.com
 * des:
 */
public class LoaderCreator {
    private static final HashMap<String, Indicator> LOADING_MAP = new HashMap<>();

    /**
     * todo:待优化
     */
    static AVLoadingIndicatorView create(String type, Context context) {
        AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type) == null) {
            Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name) {
        if (TextUtils.isEmpty(name)) {
            return null;
        }
        StringBuilder className = new StringBuilder();
        if (!name.contains(".")) {
            Package packageAVLoadingIndicatorView = AVLoadingIndicatorView.class.getPackage();
            if (packageAVLoadingIndicatorView != null) {
                String packageName = packageAVLoadingIndicatorView.getName();
                className.append(packageName).append(".indicators").append(".");
            } else {
                return null;
            }

        }
        className.append(name);
        try {
            Class clazz = Class.forName(className.toString());
            return (Indicator) clazz.newInstance();
        } catch (Exception e) {
            return null;
        }

    }


}
