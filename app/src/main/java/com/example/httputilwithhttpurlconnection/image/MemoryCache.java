package com.example.httputilwithhttpurlconnection.image;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

public class MemoryCache implements ImageCache {

    private static LruCache<String, Bitmap> lruCache;
    public static final String TAG = "MemoryCache";

    public MemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        int cacheSize = maxMemory / 4;
        lruCache = new LruCache<>(cacheSize);
    }

    public static void test() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    try {
                        Log.d(TAG, lruCache.size() + "");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        }).start();
    }


    @Override
    public void put(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
        Log.d(TAG, "put中：" + lruCache.size());

    }

    @Override
    public Bitmap get(String url) {
        Log.d(TAG, "get中：" + lruCache.size());
        return lruCache.get(url);
    }
}
