package com.example.httputilwithhttpurlconnection.threadpool;

import android.util.Log;

import com.example.httputilwithhttpurlconnection.httpurlconnection.HttpCallBackListener;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolUtil {

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    //核心线程数 = CPU核心数 + 1
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    //线程池最大线程数 = CPU核心数 * 2 + 1
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    //非核心线程闲置时超时20s
    private static final int KEEP_ALIVE = 20;

    private static ThreadPoolUtil mInstance;
    private ExecutorService executorService;
    //私有化构造方法，防止被实例化
    private ThreadPoolUtil() {
    }
    //单例模式
    public static ThreadPoolUtil getInstance() {
        if (mInstance == null) {
            mInstance = new ThreadPoolUtil();
        }
        return mInstance;
    }

    public void excute(Runnable r) {
        check();
        executorService.execute(r);
    }

    public void excute(List<Runnable> list) {
        check();
        for (Runnable command : list) {
            executorService.execute(command);
        }
    }

    public void shutDown() {
        executorService.shutdown();
    }

    public <T> Future<T> submit(Callable<T> task) {
        check();
        return executorService.submit(task);
    }

    public <T> Future<T> submit(Runnable task, T result) {
        check();
        return executorService.submit(task, result);
    }

    private void check() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20), new ThreadPoolExecutor.AbortPolicy());
        }
    }
}
