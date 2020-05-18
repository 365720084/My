package com.ptg.adsdk.lib.interf;


public interface PtgAppDownloadListener {
    void onIdle();

    void onDownloadActive(long var1, long var3, String var5, String var6);

    void onDownloadPaused(long var1, long var3, String var5, String var6);

    void onDownloadFailed(long var1, long var3, String var5, String var6);

    void onDownloadFinished(long var1, String var3, String var4);

    void onInstalled(String var1, String var2);
}
