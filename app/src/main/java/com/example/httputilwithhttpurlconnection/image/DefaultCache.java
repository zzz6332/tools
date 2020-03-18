package com.example.httputilwithhttpurlconnection.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
public class DefaultCache implements ImageCache {
   public static final String TAG = "DefaultCache";
   private  ImageCache diskCache;
   private  ImageCache memoryCache = new MemoryCache();
   private Context context;

   public DefaultCache(Context context){
       this.context = context;
       diskCache = new DiskCache(context);
   }
    @Override
    public void put(String url, Bitmap bitmap) {
      diskCache.put(url,bitmap);
      memoryCache.put(url,bitmap);
    }

    @Override
    public Bitmap get(final String url) {
        Bitmap bitmap = memoryCache.get(url);
        Log.d(TAG,bitmap + "");
        if (bitmap == null){
            bitmap = diskCache.get(url);
            Log.d(TAG,"从本地中获得到了图片");
            memoryCache.put(url,bitmap);
            return bitmap;
        }
        Log.d(TAG,"从内存中获得到了图片");
        return bitmap;
    }
}
