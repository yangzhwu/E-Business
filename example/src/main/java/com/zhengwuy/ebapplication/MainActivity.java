package com.zhengwuy.ebapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zhengwuyang.latte_core.activities.ProxyActivity;
import com.zhengwuyang.latte_core.delegatesFragment.LatteDelegateFragment;


public class MainActivity extends ProxyActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public LatteDelegateFragment setRootDelegate() {
        return new MainDelegateFragment();
    }
}
