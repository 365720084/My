package com.fancy.showmedata.network;


import android.util.Base64;

import com.fancy.showmedata.application.App;
import com.fancy.showmedata.utils.AppUtil;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BaseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl.Builder urlBuilder = original.url().newBuilder();
        HttpUrl url;
        //请求定制：添加请求头
        Request.Builder requestBuilder = original.newBuilder();
        url = urlBuilder
                .build();
        String method = original.method();
        if ("POST".equals(method)) {
            RequestBody body = original.body();

        } else {
            Map<String, Object> map = new HashMap<>();
            Set<String> par = url.queryParameterNames();
            for (String s : par) {
                map.put(s, url.queryParameter(s));
            }
        }


        Map<String, Object> map = getCommonParams();
        Set<String> keys = map.keySet();
        for (String key : keys) {

            requestBuilder.addHeader(key, String.valueOf(map.get(key)));
        }
        Request request = requestBuilder
                .url(url)
                .build();
        return chain.proceed(request);
    }




    private Map<String, Object> getCommonParams() {
        Map<String, Object> map = new HashMap<>();
        map.put("appVer", AppUtil.getVersionName(App.getAppContext()));
        map.put("platform", "android");
        map.put("osVer", AppUtil.getPlatVersion());
        map.put("model", AppUtil.getPhoneModel());
        map.put("imei", AppUtil.getAndroidId(App.getAppContext()));
        map.put("androidId", AppUtil.getAndroidId(App.getAppContext()));
        map.put("vendor", AppUtil.getPhoneBrand());
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        return map;
    }
}