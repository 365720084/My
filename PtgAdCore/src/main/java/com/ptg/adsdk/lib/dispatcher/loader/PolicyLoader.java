package com.ptg.adsdk.lib.dispatcher.loader;

public interface PolicyLoader {
    void Start(PolicyLoaderCallback callback);
    void Load(PolicyLoaderCallback callback);
}
