package com.zhengwuyang.latte_core;

/**
 * Created by zhengwuy on 2018/12/20.
 * email: 13802885114@139.com
 * des:
 */
public class Constants {
    public static class ErrorCode {
        public static final int DOWNLOAD_DIR_EMPTY_CODE = 1000;
        public static final int DOWNLOAD_WRITE_FILE_ERROR_CODE = 1001;
    }

    public static class ErrorDes {
        public static final String DOWNLOAD_DIR_EMPTY_DES = "下载文件夹不能为空";
        public static final String DOWNLOAD_WRITE_FILE_ERROR_DES = "下载文件时写入失败";
        public static final String DOWNLOAD_FILE_SUCCESS = "下载文件成功";

    }
}
