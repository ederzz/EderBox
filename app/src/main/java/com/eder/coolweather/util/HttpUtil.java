package com.eder.coolweather.util;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendOkHttpRequest(String addr, okhttp3.Callback callback) {
        Log.d("http请求地址：", addr);
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url(addr).build();
        client.newCall(req).enqueue(callback);
    }
}
