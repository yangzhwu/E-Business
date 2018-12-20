package com.zhengwuyang.latte_core.net.download;

import com.zhengwuyang.latte_core.net.RestCreator;
import com.zhengwuyang.latte_core.net.callback.INetCallBack;
import com.zhengwuyang.latte_core.net.callback.IRequest;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengwuy on 2018/12/20.
 * email: 13802885114@139.com
 * des:
 */
public class DownloadHandler {
    private String mUrl;
    private Map<String, Object> mParams;
    private IRequest mIRequest;
    private INetCallBack mINetCallBack;
    private String mDownloadFileExtension;
    private String mDownloadFileDictory;
    private String mDownloadFileName;

    public DownloadHandler(String url, Map<String, Object> params, IRequest iRequest,
                            INetCallBack iNetCallBack, String downloadFileDictory,
                            String downloadFileName, String downloadFileExtension) {
        mUrl = url;
        mParams = params;
        mIRequest = iRequest;
        mINetCallBack = iNetCallBack;
        mDownloadFileDictory = downloadFileDictory;
        mDownloadFileName = downloadFileName;
        mDownloadFileExtension = downloadFileExtension;

    }

    public void handleDownload() {
        if (mIRequest != null) {
            mIRequest.onRequestStart();
        }
        RestCreator.getRestService().download(mUrl, mParams).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
