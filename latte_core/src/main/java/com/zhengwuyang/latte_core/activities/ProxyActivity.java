package com.zhengwuyang.latte_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.zhengwuy.latte_core.R;
import com.zhengwuyang.latte_core.delegatesFragment.LatteDelegateFragment;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by zhengwuy on 2018/12/18.
 * email: 13802885114@139.com
 * des:容器activity
 */
public abstract class ProxyActivity extends SupportActivity {

    public abstract LatteDelegateFragment setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContainer(savedInstanceState);
    }

    private void initContainer(Bundle savedInstanceState) {
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(R.id.delegate_container);

        setContentView(frameLayout);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
