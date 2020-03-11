package com.example.httputilwithhttpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.httputilwithhttpurlconnection.httpurlconnection.HttpCallBackListener;
import com.example.httputilwithhttpurlconnection.threadpool.HttpRunnableThread;
import com.example.httputilwithhttpurlconnection.threadpool.ThreadPoolUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    HttpCallBackListener listener = new HttpCallBackListener() {
        @Override
        public void OnFinish(List<String> list) {
            Log.d(TAG, "回调成功");
        }

        @Override
        public void OnError() {

        }
    };
    List<String> test_list = new ArrayList<>();
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test_list.add("https://free-api.heweather.net/s6/air/now?location=重庆&key=69eb00f8b34e4c3cb3969e9a94416c70");
        test_list.add("https://free-api.heweather.net/s6/weather/now?location=重庆&key=69eb00f8b34e4c3cb3969e9a94416c70");
        test_list.add("http://gank.io/api/data/Android/10/55");

        ThreadPoolUtil util = ThreadPoolUtil.getInstance();
        HttpRunnableThread runnableThread = new HttpRunnableThread(test_list.get(0), null, listener);
        util.excute(runnableThread);
        List<Runnable> list = new ArrayList<>();
        list.add(runnableThread);
        list.add(new HttpRunnableThread(test_list.get(1), null, listener));
        list.add(new HttpRunnableThread(test_list.get(2), null, listener));
        util.excute(list);
    }
}
