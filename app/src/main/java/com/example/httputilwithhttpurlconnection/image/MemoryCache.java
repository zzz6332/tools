package com.example.httputilwithhttpurlconnection.image;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

public class MemoryCache implements ImageCache {

    LruCache<String, Bitmap> lruCache;
    public static final String TAG = "MemoryCache";

    public MemoryCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory() / 1024;
        int cacheSize = maxMemory / 4;
        lruCache = new LruCache<>(cacheSize);
    }


    @Override
    public void put(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);
        Log.d(TAG, "put中" + lruCache.size() + "");

    }

    @Override
    public Bitmap get(String url) {
        Log.d(TAG, "get中" + lruCache.size() + "");
        return lruCache.get(url);
    }
}
