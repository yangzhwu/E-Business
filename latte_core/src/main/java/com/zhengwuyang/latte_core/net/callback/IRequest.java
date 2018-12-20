package com.zhengwuyang.latte_core.net.callback;

/**
 * Created by zhengwuy on 2018/12/19.
 * email: 13802885114@139.com
 * des:
 */
public interface IRequest {
    IRequest DEFAULT = new IRequest() {
        @Override
        public void onRequestStart() {

        }

        @Override
        public void onRequestEnd() {

        }
    };

    void onRequestStart();

    void onRequestEnd();
}
