package com.zhengwuyang.latte_core.net.callback;

/**
 * Created by zhengwuy on 2018/12/19.
 * email: 13802885114@139.com
 * des:
 */
public interface INetCallBack {
    INetCallBack DEFAULT = new INetCallBack() {
        @Override
        public void onSuccess(String response) {

        }

        @Override
        public void onError(int code, String errorMessage) {

        }
    };

    void onSuccess(String response);

    void onError(int code, String errorMessage);
}
