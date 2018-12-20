package com.zhengwuyang.latte_core.net.download;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.zhengwuyang.latte_core.net.callback.INetCallBack;
import com.zhengwuyang.latte_core.net.callback.IRequest;
import com.zhengwuyang.latte_core.util.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

import static com.zhengwuyang.latte_core.Constants.ErrorCode.DOWNLOAD_DIR_EMPTY_CODE;
import static com.zhengwuyang.latte_core.Constants.ErrorCode.DOWNLOAD_WRITE_FILE_ERROR_CODE;
import static com.zhengwuyang.latte_core.Constants.ErrorDes.DOWNLOAD_DIR_EMPTY_DES;
import static com.zhengwuyang.latte_core.Constants.ErrorDes.DOWNLOAD_FILE_SUCCESS;
import static com.zhengwuyang.latte_core.Constants.ErrorDes.DOWNLOAD_WRITE_FILE_ERROR_DES;

/**
 * Created by zhengwuy on 2018/12/20.
 * email: 13802885114@139.com
 * des:
 */
public class SaveFileTask extends AsyncTask<Object, Void, File> {
    private IRequest mIRequest;
    private INetCallBack mINetCallBack;
    private File mFile;

    public SaveFileTask(IRequest iRequest, INetCallBack iNetCallBack) {
        mIRequest = iRequest;
        mINetCallBack = iNetCallBack;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downloadDir = (String) objects[0];
        String name = (String) objects[1];
        String extension = (String) objects[2];
        if (TextUtils.isEmpty(downloadDir) || TextUtils.isEmpty(name)) {
            mINetCallBack.onError(DOWNLOAD_DIR_EMPTY_CODE, DOWNLOAD_DIR_EMPTY_DES);
            return null;
        }
        ResponseBody responseBody = (ResponseBody) objects[3];
        InputStream is = responseBody.byteStream();
        File file = onFile(downloadDir, name, extension);
        if (file == null) {
            return null;
        }
        boolean saveFileSucess = FileUtil.saveToFile(file, is);
        if (saveFileSucess) {
            return file;
        } else {
            return null;
        }

    }

    @Override
    protected void onPostExecute(File file) {
        if (file == null) {
            mINetCallBack.onError(DOWNLOAD_WRITE_FILE_ERROR_CODE, DOWNLOAD_WRITE_FILE_ERROR_DES);
        } else {
            mINetCallBack.onSuccess(DOWNLOAD_FILE_SUCCESS + ": " + file.getAbsolutePath());
        }
        mIRequest.onRequestEnd();
    }

    private File onFile(String downloadDir, String name, String extension) {
        File fileDir = new File(downloadDir);
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                return null;
            }
        }
        if (!TextUtils.isEmpty(extension)) {
            if (!extension.startsWith(".")) {
                name = name + "." + extension;
            } else {
                name = name + extension;
            }
        }
        return new File(fileDir, name);
    }
}
