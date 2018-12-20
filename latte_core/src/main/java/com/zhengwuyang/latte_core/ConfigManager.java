package com.zhengwuyang.latte_core;

import android.content.Context;


import com.joanzapata.android.iconify.IconFontDescriptor;
import com.joanzapata.android.iconify.Iconify;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhengwuy on 2018/12/17.
 * email: 13802885114@139.com
 * des:
 */
public class ConfigManager {
    private final HashMap<String, Object> mConfigHashMap = new HashMap<>();
    private final List<IconFontDescriptor> mIconFontDescriptors = new LinkedList<>();
    private static ConfigManager sINSTANCE;

    public static ConfigManager init(Context context) {
        if (sINSTANCE == null) {
            synchronized (ConfigManager.class) {
                if (sINSTANCE == null) {
                    sINSTANCE = new ConfigManager();
                    sINSTANCE.mConfigHashMap.put(ConfigType.APPLICATION_CONTEXT.name(), context);
                }
            }
        }
        return sINSTANCE;
    }

    public ConfigManager withApiHost(String host) {
        mConfigHashMap.put(ConfigType.API_HOST.name(), host);
        return sINSTANCE;
    }

    public ConfigManager withIcon(IconFontDescriptor descriptor) {
        mIconFontDescriptors.add(descriptor);
        return this;
    }

    public ConfigManager config() {
        initIcons();
        return this;
    }

    public static ConfigManager getInstance() {
        if (sINSTANCE == null) {
            throw new RuntimeException("configManager has not be init, please call init()!");
        }
        return sINSTANCE;
    }


    private void put(String key, Object config) {
        mConfigHashMap.put(key, config);
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfig(ConfigType configType) {
        return (T) mConfigHashMap.get(configType.name());
    }

    private void initIcons() {
        for (IconFontDescriptor mIconFontDescriptor : mIconFontDescriptors) {
            Iconify.with(mIconFontDescriptor);
        }
    }


    public enum ConfigType {
        API_HOST,
        APPLICATION_CONTEXT,
        CONFIG_READY,
        ICON
    }
}
