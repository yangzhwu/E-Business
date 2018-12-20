package com.zhengwuy.latte_ec;
;
import com.joanzapata.android.iconify.Icon;
import com.joanzapata.android.iconify.IconFontDescriptor;
import com.zhengwuy.latte_ec.icon.EcIcons;

/**
 * Created by zhengwuy on 2018/12/17.
 * email: 13802885114@139.com
 * des:
 */
public class FontEcModule implements IconFontDescriptor {

    @Override
    public String ttfFileName() {
        return "iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
