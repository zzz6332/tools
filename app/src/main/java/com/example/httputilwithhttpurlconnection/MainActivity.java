package com.example.httputilwithhttpurlconnection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.httputilwithhttpurlconnection.httpurlconnection.HttpCallBackListener;
import com.example.httputilwithhttpurlconnection.image.DefaultCache;
import com.example.httputilwithhttpurlconnection.image.DiskCache;
import com.example.httputilwithhttpurlconnection.image.ImageLoader;
import com.example.httputilwithhttpurlconnection.image.MemoryCache;
import com.example.httputilwithhttpurlconnection.threadpool.HttpRunnableThread;
import com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public String[] permissions = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.MOUNT_UNMOUNT_FILESYSTEMS"};
    ImageView imageView;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)){
                    Toast.makeText(MainActivity.this,"当前无法使用本地缓存功能",Toast.LENGTH_SHORT).show();
                }

        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);  if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"111111");
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
        imageView = findViewById(R.id.iv);
        ImageLoader loader = new ImageLoader(new DefaultCache(MainActivity.this));
        loader.display(imageView, "http://img0.imgtn.bdimg.com/it/u=2374647389,52685935&fm=26&gp=0.jpg");
        MemoryCache.test();
    }
}
