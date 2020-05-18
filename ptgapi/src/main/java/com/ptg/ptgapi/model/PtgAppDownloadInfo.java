package com.ptg.ptgapi.model;

public class PtgAppDownloadInfo {
    private long a;
    private int b;
    private long c;
    private long d;
    private String e;
    private String f;

    public PtgAppDownloadInfo() {
    }

    public long getId() {
        return this.a;
    }

    public void setId(long var1) {
        this.a = var1;
    }

    public int getInternalStatusKey() {
        return this.b;
    }

    public void setInternalStatusKey(int var1) {
        this.b = var1;
    }

    public long getTotalBytes() {
        return this.c;
    }

    public void setTotalBytes(long var1) {
        this.c = var1;
    }

    public long getCurrBytes() {
        return this.d;
    }

    public void setCurrBytes(long var1) {
        this.d = var1;
    }

    public String getFileName() {
        return this.e;
    }

    public void setFileName(String var1) {
        this.e = var1;
    }

    public String getAppName() {
        return this.f;
    }

    public void setAppName(String var1) {
        this.f = var1;
    }
}
