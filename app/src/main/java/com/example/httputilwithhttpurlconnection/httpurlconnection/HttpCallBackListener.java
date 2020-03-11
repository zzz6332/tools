package com.example.httputilwithhttpurlconnection.httpurlconnection;

import java.util.List;
import java.util.Map;

import javax.security.auth.callback.Callback;

public interface HttpCallBackListener  {
    void OnFinish(List<String> list);
    void OnError();
}
