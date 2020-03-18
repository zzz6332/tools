package com.example.httputilwithhttpurlconnection.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.example.httputilwithhttpurlconnection.MainActivity;
import com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DiskCache implements ImageCache {
    private Context context;
    public static final String TAG = "DiskCache";
    private ThreadPoolUtil util = ThreadPoolUtil.getInstance(); //线程池工具类

    public DiskCache(Context context) {
        this.context = context;
    }

    @Override
    public void put(final String url, final Bitmap bitmap) {
        util.excute(new Runnable() {
            @Override
            public void run() {
                File file = new File(context.getCacheDir(), url.substring(url.lastIndexOf("/")));
                FileOutputStream outputStream = null;
                try {
                    Log.d(TAG,file.toString());
                    outputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    Log.d(TAG, "sd卡放了缓存");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public Bitmap get(String url) {
        return BitmapFactory.decodeFile(context.getCacheDir() + "/" + url.substring(url.lastIndexOf("/")));
    }

}
