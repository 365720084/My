package com.ptg.adsdk.lib.interf;

import com.ptg.adsdk.lib.model.PtgAppDownloadInfo;

public interface PtgGlobalAppDownloadListener {
    void onDownloadActive(PtgAppDownloadInfo var1);

    void onDownloadPaused(PtgAppDownloadInfo var1);

    void onDownloadFinished(PtgAppDownloadInfo var1);

    void onInstalled(String var1, String var2, long var3, int var5);

    void onDownloadFailed(PtgAppDownloadInfo var1);
}
