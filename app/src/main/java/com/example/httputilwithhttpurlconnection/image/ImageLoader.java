package com.example.httputilwithhttpurlconnection.image;

import android.content.res.Resources;
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
    public static final int SUCCESS = 1;
    public static final int LOADING = 2;
    public static final int FAIL = 3;
    private Bitmap bitmap;
    private int loadId;
    private int failId;
    private ImageView imageView;
    private ThreadPoolUtil poolUtil;
    private ImageCache cache;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    imageView.setImageBitmap(bitmap); //加载成功后设置图片
                    break;
                case LOADING:
                    imageView.setImageResource(loadId); //设置加载时的图片
                    break;
                case FAIL:
                    imageView.setImageResource(failId); //设置加载失败后的图片
            }
        }
    };

    public ImageLoader(ImageCache imageCache, int loadId, int failId) { //初始化图片加载的策略
        cache = imageCache;
        this.loadId = loadId;
        this.failId = failId;
        poolUtil = ThreadPoolUtil.getInstance();
    }

    public void setLoadId(int loadId) {
        this.loadId = loadId;
    }

    public void setFailId(int failId) {
        this.failId = failId;
    }

    public void display(ImageView imageView, String url,int reqWidth,int reqHeight) {
        this.imageView = imageView;
        if (loadId!=0)
            imageView.setImageResource(loadId);
        bitmap = cache.get(url,reqWidth,reqHeight);
        if (bitmap != null) {
            Log.d("TAG", "从Cache中获取到了bitmap");
            imageView.setImageBitmap(bitmap);
            return;
        }
        loadFromNet(imageView, url,reqWidth,reqHeight);
    }

    public void display(ImageView imageView, String url){
        display(imageView,url,0,0);
    }
    public void display(ImageView imageView, Resources resources,int id,int reqWidth,int reqHeight){
        this.imageView = imageView;
        if (loadId!=0){
            imageView.setImageResource(loadId);
        }
        imageView.setImageBitmap(MyBitmapFactory.decodeRes(resources,id,reqWidth,reqHeight));
    }


    private void loadFromNet(final ImageView imageView, final String url, final int reqWidth, final int reqHeight) {
        imageView.setTag(url);
        poolUtil.excute(new Runnable() {
            @Override
            public void run() {
                bitmap = downloadBitmap(url,reqWidth,reqHeight);
                if (bitmap == null) {
                    Log.d(TAG, "loadFromNet：从网络上加载失败");
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    Log.d(TAG, "loadFromNet：设置了图片");
                    Message message = new Message();
                    message.what = SUCCESS;
                    handler.sendMessage(message);
                }
                cache.put(url, bitmap);
                Log.d(TAG, "loadFromNet：放了缓存");

            }
        });
    }

    private Bitmap downloadBitmap(final String imageUrl,int reqWidth,int reqHeight) {
        try {
            URL url = new URL(imageUrl);
            bitmap = MyBitmapFactory.decodeStream(url,reqWidth,reqHeight);
            Log.d(TAG, "downloadBitmap");

        } catch (Exception e) {
            e.printStackTrace();
            Message message = new Message();
            message.what = FAIL;
            handler.sendMessage(message);
        }
        return bitmap;


    }
}
