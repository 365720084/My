package com.ptg.adsdk.lib.interf;

import androidx.annotation.MainThread;


import java.util.List;

public interface PtgFeedAdListener {
    @MainThread
    void onError(int var1, String var2);


    @MainThread
    void onFeedAdLoad(List<PtgFeedAd> var1);
}
