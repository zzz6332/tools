package com.example.httputilwithhttpurlconnection.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DiskCache implements ImageCache {
    public static String cacheDir = "sdcard/cache/";
    private ThreadPoolUtil util = ThreadPoolUtil.getInstance();

    @Override
    public void put(final String url, final Bitmap bitmap) {
        util.excute(new Runnable() {
            @Override
            public void run() {
                try {
                    FileOutputStream outputStream = new FileOutputStream(cacheDir + url);
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
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
