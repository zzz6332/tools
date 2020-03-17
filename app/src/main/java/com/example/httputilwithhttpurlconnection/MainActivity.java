package com.example.httputilwithhttpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.httputilwithhttpurlconnection.httpurlconnection.HttpCallBackListener;
import com.example.httputilwithhttpurlconnection.image.DefaultCache;
import com.example.httputilwithhttpurlconnection.image.ImageLoader;
import com.example.httputilwithhttpurlconnection.image.MemoryCache;
import com.example.httputilwithhttpurlconnection.threadpool.HttpRunnableThread;
import com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> test_list = new ArrayList<>();
    public static final String TAG = "MainActivity";
    ImageView imageView;
    HttpCallBackListener listener = new HttpCallBackListener() {
        @Override
        public void OnFinish(List<String> list) {
            Log.d(TAG, "回调成功");
        }

        @Override
        public void OnError() {

        }
    };


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.iv);
        ImageLoader loader = new ImageLoader(new MemoryCache());
        loader.display(imageView,"https://iknow-pic.cdn.bcebos.com/1b4c510fd9f9d72a1871ecadda2a2834349bbb6d");
    }
}
