package com.example.httputilwithhttpurlconnection.threadpool;

import com.example.httputilwithhttpurlconnection.httpurlconnection.HttpCallBackListener;
import com.example.httputilwithhttpurlconnection.httpurlconnection.HttpUtil;

import java.util.List;


public class HttpRunnableThread implements Runnable {
    private String url;
    private List<String> need_list;
    private HttpCallBackListener listener;

    public HttpRunnableThread(String url, List<String> need_list, HttpCallBackListener listener) {
        this.url = url;
        this.need_list = need_list;
        this.listener = listener;
    }
    public HttpRunnableThread() {
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNeed_list(List<String> need_list) {
        this.need_list = need_list;
    }

    public void setListener(HttpCallBackListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {
        HttpUtil.sendHttpWithUrlConnection(url, need_list, listener);
    }
}
