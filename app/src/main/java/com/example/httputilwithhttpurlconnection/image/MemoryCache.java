package com.example.httputilwithhttpurlconnection.image;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

public class MemoryCache implements ImageCache {

    private static LruCache<String, Bitmap> lruCache;
    public static final String TAG = "MemoryCache";
    private static ImageCache cache;


    private MemoryCache() {
    }
    public static ImageCache getInstance(){
        if (cache == null){
            synchronized (MemoryCache.class){
                cache = new MemoryCache();
                int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
                int cacheSize = maxMemory / 4;
                lruCache = new LruCache<String,Bitmap>(cacheSize);
            }
        }
        return cache;
    }
    @Override
    public void put(String url, Bitmap bitmap) {
        lruCache.put(url.substring(url.lastIndexOf("/")), bitmap);
        Log.d(TAG, "put中：" + lruCache.size());

    }

    @Override
    public Bitmap get(String url) {
        Log.d(TAG, "get中：" + lruCache.size());
        return lruCache.get(url.substring(url.lastIndexOf("/")));
    }
}
