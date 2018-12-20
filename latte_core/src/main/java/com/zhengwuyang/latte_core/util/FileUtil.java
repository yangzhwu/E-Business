package com.zhengwuyang.latte_core.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhengwuy on 2018/12/20.
 * email: 13802885114@139.com
 * des:
 */
public class FileUtil {
    private static String TAG = "FileUtil";

    public static boolean saveToFile(File file, InputStream inputStream) {
        if (file.exists()) {
            if (file.delete()) {
                try {
                    if (file.createNewFile()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        writFile(fileOutputStream, inputStream);
                        return true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            try {
                if (file.createNewFile()) {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    writFile(fileOutputStream, inputStream);
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;

    }

    private static void writFile(FileOutputStream fileOutputStream, InputStream inputStream) throws IOException {
        if (fileOutputStream == null || inputStream == null) {
            return;
        }
        byte[] bytes = new byte[4096];
        int length;
        Log.d(TAG, inputStream.available() + " length bytes will be write");
        while ((length = inputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, length);
        }
        inputStream.close();
        fileOutputStream.close();
    }
}
