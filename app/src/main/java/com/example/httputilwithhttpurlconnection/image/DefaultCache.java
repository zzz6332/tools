package com.example.httputilwithhttpurlconnection.image;

import android.graphics.Bitmap;

import com.example.httputilwithhttpurlconnection.httpurlconnection.HttpUtil;
import com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;

public class DefaultCache implements ImageCache {

   private ImageCache diskCache = new DiskCache();
   private ImageCache memoryCache = new MemoryCache();
    @Override
    public void put(String url, Bitmap bitmap) {
      diskCache.put(url,bitmap);
      memoryCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(final String url) {
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap == null){
            bitmap = diskCache.get(url);
        }

        return bitmap;
    }
}
