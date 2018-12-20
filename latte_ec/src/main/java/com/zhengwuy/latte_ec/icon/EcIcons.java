package com.zhengwuy.latte_ec.icon;

import com.joanzapata.android.iconify.Icon;

/**
 * Created by zhengwuy on 2018/12/17.
 * email: 13802885114@139.com
 * des:
 */
public enum EcIcons implements Icon {
    icon_alipay('\ue613');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return null;
    }

    @Override
    public char character() {
        return 0;
    }
}
