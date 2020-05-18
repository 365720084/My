package com.ptg.ptgapi.model;

public interface Callback<T> {
    void onSuccess(T object);
    void onError(AdError error);
}
