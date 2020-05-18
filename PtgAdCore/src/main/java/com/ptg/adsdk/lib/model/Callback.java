package com.ptg.adsdk.lib.model;

public interface Callback<T> {
    void onSuccess(T object);
    void onError(AdError error);
}
