package com.example.httputilwithhttpurlconnection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    ImageView imageView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.iv);
        List<String> test = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.rv);
        test.add("http://img4.imgtn.bdimg.com/it/u=1437445163,833182487&fm=26&gp=0.jpg");
        test.add("http://img3.imgtn.bdimg.com/it/u=2327673368,864828007&fm=26&gp=0.jpg");
        test.add("http://img4.imgtn.bdimg.com/it/u=2127744929,2525013243&fm=26&gp=0.jpg");
        MyAdapter adapter = new MyAdapter(MainActivity.this,test);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
