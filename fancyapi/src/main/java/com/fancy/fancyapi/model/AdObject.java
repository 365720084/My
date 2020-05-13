package com.fancy.fancyapi.model;

import java.util.List;

public class AdObject {
    private List<Ad> ad;
    private String version;
    private long process_time;
    private String reqid;
    private int code;
    private String error_message;

    public List<Ad> getAd() {
        return ad;
    }

    public void setAd(List<Ad> ad) {
        this.ad = ad;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getProcess_time() {
        return process_time;
    }

    public void setProcess_time(long process_time) {
        this.process_time = process_time;
    }

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String toString() {
        return "AdObject{" +
                "ad=" + ad +
                ", version='" + version + '\'' +
                ", process_time=" + process_time +
                ", reqid='" + reqid + '\'' +
                ", code=" + code +
                ", error_message='" + error_message + '\'' +
                '}';
    }
}
