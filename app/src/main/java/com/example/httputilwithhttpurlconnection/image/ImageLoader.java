package com.example.httputilwithhttpurlconnection.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;

import android.os.Handler;

import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader {
    public static String TAG = "ImageLoader";
    private Bitmap bitmap;
    private ImageView imageView;
    private ThreadPoolUtil poolUtil;
    private ImageCache cache = new DefaultCache();
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    imageView.setImageBitmap(bitmap);
            }
        }
    };

    public ImageLoader(ImageCache imageCache) { //初始化图片加载的策略
        cache = imageCache;
    }

    public void display(ImageView imageView, String url) {
        this.imageView = imageView;
        bitmap = cache.get(url);
        Log.d(TAG, "第一步");
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        loadFromNet(imageView, url);
    }

    private void loadFromNet(final ImageView imageView, final String url) {
        poolUtil = ThreadPoolUtil.getInstance();
        imageView.setTag(url);

        poolUtil.excute(new Runnable() {
            @Override
            public void run() {
                bitmap = downloadBitmap(url);
                if (bitmap == null) {
                    Log.d(TAG, "loadFromNet是空的");
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    Log.d(TAG, "loadFromNet设置了图片");
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
                cache.put(url, bitmap);
                Log.d(TAG, "loadFromNet放了缓存");

            }
        });
    }

    private Bitmap downloadBitmap(final String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            Log.d(TAG, "dowmloadBitmap");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            Log.d(TAG, "dowmloadBitmap2");
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;


    }
}
