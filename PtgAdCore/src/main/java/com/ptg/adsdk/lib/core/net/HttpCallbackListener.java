package com.ptg.adsdk.lib.core.net;

public interface HttpCallbackListener {
    boolean onPreRequest(HttpJobMsg httpJobMsg);

    void onResult(HttpJobMsg httpJobMsg, int i, String str);
}
