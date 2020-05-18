package com.ptg.adsdk.lib.core.model;


import com.ptg.adsdk.lib.model.AdError;

public class AdErrorImpl implements AdError {


    private int errorCode;
    private String message;
    private Throwable throwable;

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public AdErrorImpl(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public AdErrorImpl(int errorCode, String message, Throwable throwable) {
        this.errorCode = errorCode;
        this.message = message;
        this.throwable = throwable;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public static AdErrorImpl networkError(int code) {
        return new AdErrorImpl(code, "network error");
    }

    public static AdErrorImpl responseError(String message) {
        return new AdErrorImpl(ERROR_RESPONSE, message);
    }
    public static AdErrorImpl noADError() {
        return new AdErrorImpl(ERROR_NO_AD, ERROR_NO_AD_STR);
    }
    public static AdErrorImpl networkError(Throwable throwable) {
        return new AdErrorImpl(AdErrorImpl.ERROR_NETWORK, "network error", throwable);
    }

    public static AdErrorImpl unkownError(Throwable throwable) {
        return new AdErrorImpl(AdErrorImpl.ERROR_UNKOWN, "unknown error", throwable);
    }

    @Override
    public String toString() {
        return "AdError{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                ", throwable=" + throwable +
                '}';
    }
}
