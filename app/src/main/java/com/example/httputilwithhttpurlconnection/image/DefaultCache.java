package com.example.httputilwithhttpurlconnection.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

public class DefaultCache implements ImageCache {
    public static final String TAG = "DefaultCache";
    private ImageCache diskCache;
    private ImageCache memoryCache;
    private Context context;

    public DefaultCache(Context context) {
        this.context = context;
        diskCache = new DiskCache(context);
        memoryCache = MemoryCache.getInstance();
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        diskCache.put(url, bitmap);
        memoryCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(final String url,int reqWdith,int reqHeight) {
        Bitmap bitmap = memoryCache.get(url);
        Log.d(TAG, bitmap + "");
        if (bitmap == null) {
            bitmap = diskCache.get(url,reqWdith,reqHeight);
            if (bitmap != null) {
                Log.d(TAG, "从本地中获得到了图片");
                memoryCache.put(url, bitmap);
                return bitmap;
            }
        }
        Log.d(TAG, "从内存中获得到了图片");
        return bitmap;
    }

    @Override
    public Bitmap get(String url) {
        return get(url,0,0);
    }
}
