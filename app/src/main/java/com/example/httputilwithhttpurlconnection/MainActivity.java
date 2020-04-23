package com.example.httputilwithhttpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.httputilwithhttpurlconnection.databinding.ActivityMainBinding;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    ImageView imageView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setStu(new Student("陈十一","!"));
        Student student = new Student();
       // setContentView(R.layout.activity_main);
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
