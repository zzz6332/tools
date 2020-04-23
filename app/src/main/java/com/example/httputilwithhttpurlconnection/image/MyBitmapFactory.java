package com.example.httputilwithhttpurlconnection.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.BreakIterator;

public class MyBitmapFactory {

    /**
     *这个类用来压缩图片
     */
    public static final String TAG = "MyBitmapFactory";
    public static Bitmap decodeFile(String pathname, int reqWidth,int reqHeight){
        if (reqHeight > 0 || reqWidth > 0){
            try{
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(pathname,options);
                options.inSampleSize = calculate(options,reqWidth,reqHeight);
                options.inJustDecodeBounds = false;
                Log.d(TAG,"解码文件");
                return BitmapFactory.decodeFile(pathname,options);
            }catch (Exception e){
                return null;
            }
        }
        else {
            return   BitmapFactory.decodeFile(pathname);
        }

    }
    public static Bitmap decodeStream(URL url, int reqWidth, int reqHeigt){
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream   inputStream = connection.getInputStream();
            if (reqHeigt > 0 || reqWidth > 0) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(inputStream, null, options);
                options.inJustDecodeBounds = false;
                options.inSampleSize = calculate(options,reqWidth,reqHeigt);
                HttpURLConnection connection1 = (HttpURLConnection) url.openConnection();
                InputStream is = connection1.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(is,null,options);
                Log.d(TAG,"解码流！！！！！" + bitmap);
                connection.disconnect();
                connection1.disconnect();
                inputStream.close();
                is.close();
                return bitmap;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
    public static Bitmap decodeRes(Resources res,int id,int reqWidth,int reqHeight){
        if (reqHeight > 0 || reqWidth > 0){
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res,id,options);
            options.inJustDecodeBounds = false;
            options.inSampleSize = calculate(options,reqWidth,reqHeight);
            return BitmapFactory.decodeResource(res,id,options);
        }
       else {
           return BitmapFactory.decodeResource(res,id);
        }
    }
    private static int calculate(BitmapFactory.Options options,int reqWidth,int reqHeight){
        int width = options.outWidth;
        int heigh = options.outHeight;
        int sampleSize = 1;
        if (width > reqHeight || heigh > reqHeight){
            int halfWidth = width/2;
            int halfHeigh = heigh/2;
            while (halfHeigh > reqHeight && halfWidth > reqWidth){
                sampleSize *= 2;
                halfHeigh /= 2;
                halfWidth /= 2;
            }
        }
        return  sampleSize;
    }
}
