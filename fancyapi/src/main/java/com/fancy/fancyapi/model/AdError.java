package com.fancy.fancyapi.model;

public interface AdError {

    public static final int ERROR_NO_INIT = 1001;
    public static final int ERROR_PARAM = 1002;
    public static final int ERROR_NETWORK = 1003;
    public static final int ERROR_RESPONSE = 1004;
    public static final int ERROR_UNKOWN = 1005;

    int getErrorCode();

    String getMessage();

    Throwable getThrowable();



}
