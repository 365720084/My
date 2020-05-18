package com.ptg.adsdk.lib.model;

public interface AdError {

    public static final int ERROR_NO_INIT = 1001;
    public static final int ERROR_PARAM = 1002;
    public static final String ERROR_UNKOWN_STR = "1005";
    public static final int ERROR_NETWORK = 1003;
    public static final String ERROR_NETWORK_STR = "1003";

    public static final int ERROR_RESPONSE = 1004;
    public static final int ERROR_NO_AD= 1006;
    public static final String ERROR_NO_AD_STR = "没有获取到广告";

    public static final int ERROR_UNKOWN = 1005;



    int getErrorCode();

    String getMessage();

    Throwable getThrowable();



}
