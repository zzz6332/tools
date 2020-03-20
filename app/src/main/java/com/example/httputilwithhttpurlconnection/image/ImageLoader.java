package com.example.httputilwithhttpurlconnection.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.session.MediaSession;
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
    private int loadId;
    private int failId;
    private ImageView imageView;
    private ThreadPoolUtil poolUtil;
    private ImageCache cache;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    imageView.setImageBitmap(bitmap);
                    break;
                case 2:
                    imageView.setImageResource(loadId);
                    break;
                case 3:
                    imageView.setImageResource(failId);
            }
        }
    };

    public ImageLoader(ImageCache imageCache,int loadId,int failId) { //初始化图片加载的策略
        cache = imageCache;
        this.loadId = loadId;
        this.failId = failId;
    }

    public void display(ImageView imageView, String url) {
        this.imageView = imageView;
        bitmap = cache.get(url);
        imageView.setImageResource(loadId);
        if (bitmap != null) {
            Log.d("TAG","从Cache中获取到了bitmap");
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
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
            Log.d(TAG, "dowmloadBitmap");
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            Message message = new Message();
            message.what = 3;
            handler.sendMessage(message);
        }
        return bitmap;


    }
}
