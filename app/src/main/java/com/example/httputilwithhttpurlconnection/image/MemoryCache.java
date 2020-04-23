package com.example.httputilwithhttpurlconnection.image;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import java.lang.ref.SoftReference;

public class MemoryCache implements ImageCache {

    private static LruCache<String, SoftReference<Bitmap>> lruCache;
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
                lruCache = new LruCache<String,SoftReference<Bitmap>>(cacheSize);
            }
        }
        return cache;
    }
    @Override
    public void put(String url, Bitmap bitmap) {
        SoftReference<Bitmap> softReference = new SoftReference<>(bitmap);
        lruCache.put(url.substring(url.lastIndexOf("/")), softReference);
        Log.d(TAG, "put中：" + lruCache.size());

    }

    @Override
    public Bitmap get(String url,int reqWdith,int reqHeight) {
        return get(url);
    }

    @Override
    public Bitmap get(String url) {
        Log.d(TAG, "get中：" + lruCache.size());
        if (lruCache.get(url.substring(url.lastIndexOf("/")))==null)
            return null;
        else
            return lruCache.get(url.substring(url.lastIndexOf("/"))).get();
    }
}
