package com.fancy.showmedata.network;

/**
 * Created by weitf on 16/4/7.
 */
public class BaseResponse<T> {
    private int code;
    private String message;
    private String showErr;
    private long currentTime;
    public T data;

    public String getShowErr() {
        return showErr;
    }

    public void setShowErr(String showErr) {
        this.showErr = showErr;
    }
    //    private T datas;


    public T getData() {
        return data;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public void setData(T data) {
        this.data = data;
    }
}
