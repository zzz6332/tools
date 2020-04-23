package com.example.httputilwithhttpurlconnection.image;

import android.graphics.Bitmap;

public interface ImageCache {
    void put(String url, Bitmap bitmap);
    Bitmap get(String url,int reqWdith,int reqHeight);
    Bitmap get(String url);
}
