package com.ptg.adsdk.lib.dispatcher.loader;

import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicy;

public interface PolicyLoaderCallback {
    void OnSuccess(DispatchPolicy policy);
    void OnError(String message);
}
