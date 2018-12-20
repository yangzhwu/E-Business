package com.zhengwuyang.latte_core.net;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDialogFragment;

import com.zhengwuyang.latte_core.net.callback.INetCallBack;
import com.zhengwuyang.latte_core.net.callback.IRequest;
import com.zhengwuyang.latte_core.net.download.DownloadHandler;
import com.zhengwuyang.latte_core.ui.LatteLoader;
import com.zhengwuyang.latte_core.ui.LoaderType;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengwuy on 2018/12/19.
 * email: 13802885114@139.com
 * des:
 */
public class RestClient {
    private String mUrl;
    private Map<String, Object> mParams;
    private IRequest mIRequest;
    private INetCallBack mINetCallBack;
    private RequestBody mRequestBody;
    private LoaderType mLoaderType;
    private WeakReference<FragmentActivity> mFragmentActivityWeakReference;
    private AppCompatDialogFragment appCompatDialogFragment;
    private File mFile;
    private String mDownLoadFileDictory;
    private String mDownLoadFileName;
    private String mDownLoadFileExtension;

    private RestClient(Builder builder) {
        mUrl = builder.mUrl;
        mParams = builder.mParams;
        mIRequest = builder.mIRequest;
        mINetCallBack = builder.mINetCallBack;
        mRequestBody = builder.mRequestBody;
        mLoaderType = builder.mLoaderType;
        if (builder.mFragmentActivity != null) {
            mFragmentActivityWeakReference = new WeakReference<>(builder.mFragmentActivity);
        }
        mFile = builder.mFile;

        //下载文件的地址，分别为目录，文件名，扩展名
        mDownLoadFileDictory = builder.mDownloadFileDictory;
        mDownLoadFileName = builder.mDownLoadFileName;
        mDownLoadFileExtension = builder.mDownLoadFileExtension;

    }

    public static class Builder {
        private String mUrl;
        private Map<String, Object> mParams;
        private IRequest mIRequest;
        private INetCallBack mINetCallBack;
        private RequestBody mRequestBody;
        private LoaderType mLoaderType;
        private FragmentActivity mFragmentActivity;
        private File mFile;
        private String mDownLoadFileName;
        private String mDownLoadFileExtension;
        private String mDownloadFileDictory;

        public Builder() {
            mParams = new HashMap<>();
            mIRequest = IRequest.DEFAULT;
            mINetCallBack = INetCallBack.DEFAULT;
        }

        public Builder url(String url) {
            mUrl = url;
            return this;
        }

        public Builder params(@NonNull Map<String, Object> params) {
            mParams = params;
            return this;
        }

        public Builder params(String key, Object value) {
            mParams.put(key, value);
            return this;
        }

        public Builder request(IRequest request) {
            mIRequest = request;
            return this;
        }

        public Builder netCallBack(INetCallBack netCallBack) {
            mINetCallBack = netCallBack;
            return this;
        }

        public Builder raw(String raw) {
            mRequestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
            return this;
        }

        public Builder loaderType(LoaderType loaderType, FragmentActivity fragmentActivity) {
            mLoaderType = loaderType;
            mFragmentActivity = fragmentActivity;
            return this;
        }

        public Builder File(File file) {
            mFile = file;
            return this;
        }

        public Builder File(String path) {
            mFile = new File(path);
            return this;
        }

        public Builder downloadFileName(String downLoadFileName) {
            mDownLoadFileName = downLoadFileName;
            return this;
        }

        public Builder downloadFileDictory(String downloadFileDictory) {
            mDownloadFileDictory = downloadFileDictory;
            return this;
        }

        public Builder downloadFileExtension(String downLoadFileExtension) {
            mDownLoadFileExtension = downLoadFileExtension;
            return this;
        }

        public Builder loaderType(FragmentActivity fragmentActivity) {
            mLoaderType = LoaderType.BallSpinFadeLoaderIndicator;
            mFragmentActivity = fragmentActivity;
            return this;
        }

        public RestClient build() {
            return new RestClient(this);
        }
    }

    public void get() {
        request(HttpMethod.GET);
    }

    public void post() {
        if (mRequestBody == null) {
            request(HttpMethod.POST);
        } else {
            request(HttpMethod.POST_RAW);
        }
    }

    public void put() {
        if (mRequestBody == null) {
            request(HttpMethod.PUT);
        } else {
            request(HttpMethod.PUT_RAW);
        }
    }

    public void download() {
        DownloadHandler downloadHandler = new DownloadHandler(mUrl, mParams, mIRequest, mINetCallBack,
                mDownLoadFileDictory, mDownLoadFileName, mDownLoadFileExtension);
        downloadHandler.handleDownload();
    }

    private void request(HttpMethod httpMethod) {
        RestService restService = RestCreator.getRestService();
        Call<String> call = null;
        mIRequest.onRequestStart();
        switch (httpMethod) {
            case GET:
                call = restService.get(mUrl, mParams);
                break;
            case POST:
                call = restService.post(mUrl, mParams);
                break;
            case PUT:
                call = restService.put(mUrl, mParams);
                break;
            case DELETE:
                call = restService.delete(mUrl, mParams);
                break;
            case UPLOAD:
                RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), mFile);
                MultipartBody.Part part = MultipartBody.Part.createFormData("file", mFile.getName(), requestBody);
                call = restService.upload(mUrl, part);
                break;
            case POST_RAW:
                call = restService.postRaw(mUrl, mRequestBody);
                break;
            case PUT_RAW:
                call = restService.putRaw(mUrl, mRequestBody);
                break;
            case DOWNLOAD:
                break;
                default:
                    break;
        }
        if (call != null) {
            if (mLoaderType != null && mFragmentActivityWeakReference != null && mFragmentActivityWeakReference.get() != null) {
                appCompatDialogFragment = LatteLoader.createLoadingDialog(mFragmentActivityWeakReference.get(), mLoaderType);
                appCompatDialogFragment.show(mFragmentActivityWeakReference.get().getSupportFragmentManager(), mLoaderType.name());
            }
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                    dissmissDialog();
                    if (response.isSuccessful()) {
                        mINetCallBack.onSuccess(response.body());
                    } else {
                        mINetCallBack.onError(response.code(), response.message());
                    }
                    mIRequest.onRequestEnd();
                }

                @Override
                public void onFailure(@NonNull Call<String> call, Throwable t) {
                    dissmissDialog();
                    mINetCallBack.onError(-1, t.getMessage());
                    mIRequest.onRequestEnd();
                }
            });
        }

    }

    private void dissmissDialog() {
        if (appCompatDialogFragment != null && appCompatDialogFragment.isCancelable()) {
            appCompatDialogFragment.dismiss();
            appCompatDialogFragment = null;
        }
    }

}
