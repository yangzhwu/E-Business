package com.zhengwuy.ebapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.android.iconify.views.IconTextView;
import com.zhengwuy.latte_core.R2;
import com.zhengwuyang.latte_core.delegatesFragment.LatteDelegateFragment;
import com.zhengwuyang.latte_core.net.RestClient;
import com.zhengwuyang.latte_core.net.callback.INetCallBack;

import butterknife.BindView;

/**
 * Created by zhengwuy on 2018/12/18.
 * email: 13802885114@139.com
 * des:
 */
public class MainDelegateFragment extends LatteDelegateFragment {

    @BindView(R.id.response_tv)
    TextView mTextView;

    @BindView(R.id.icon_test)
    IconTextView mIconTextView;

    @Override
    protected Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mIconTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRestClient();
            }
        });
    }

    private void testRestClient() {
        Toast.makeText(getActivity(), "start request", Toast.LENGTH_SHORT).show();
        RestClient restClient = new RestClient.Builder().url("http://news.baidu.com/")
                .loaderType(getActivity())
                .netCallBack(new INetCallBack() {
                    @Override
                    public void onSuccess(String response) {
                        mTextView.setText(response);
                    }

                    @Override
                    public void onError(int code, String errorMessage) {
                        mTextView.setText(code + ": "+ errorMessage);
                    }
                }).build();
        restClient.get();
    }
}
