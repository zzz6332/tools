package com.example.httputilwithhttpurlconnection.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DiskCache implements ImageCache {
    public static String cacheDir = "sdcard/cache/";

    public static final String TAG = "DiskCache";
    private ThreadPoolUtil util = ThreadPoolUtil.getInstance(); //线程池工具类

    @Override
    public void put(final String url, final Bitmap bitmap) {
        util.excute(new Runnable() {
            @Override
            public void run() {
                    File file = new File(cacheDir + "jij.txt" );
                    if (!file.exists()) {
                        try {
                            file.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(cacheDir + url);
                    Log.d(TAG,"1111");
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                    Log.d(TAG,"2222");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(cacheDir + url);
    }
}
