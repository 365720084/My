package com.ptg.adsdk.lib.core.net;

import java.util.HashMap;
import java.util.Map;

public class HttpJobMsg {
    public static final String GET = "GET";
    public static final int NO_RECV_FROM_SERVER = 1;
    public static final String POST = "POST";
    public String url;
    public HttpCallbackListener callbackListener = NetUtils.dumbListener;
    public Map<String, String> header = new HashMap<>();
    public Map<String, Object> formData = new HashMap<>();
    public String method = GET;
    public int msgFlags = 0;
    public String postData;

    public HttpJobMsg(String url) {
        this.url = url;
    }
}
