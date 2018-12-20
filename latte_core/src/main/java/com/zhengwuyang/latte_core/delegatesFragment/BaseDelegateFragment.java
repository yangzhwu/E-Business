package com.zhengwuyang.latte_core.delegatesFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by zhengwuy on 2018/12/18.
 * email: 13802885114@139.com
 * des:
 */
public abstract class BaseDelegateFragment extends SwipeBackFragment {
    private Unbinder mUnbinder = null;

    protected abstract Object setLayout();

    protected abstract void onBindView(Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        Object object = setLayout();
        if (object instanceof Integer) {
            rootView = inflater.inflate((Integer) object, container, false);
        } else if (object instanceof View) {
            rootView = (View) object;
        }
        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }

        return rootView;
    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
        super.onDestroyView();
    }
}
