package com.fancy.fancyapi.model;

public interface Callback<T> {
    void onSuccess(T object);
    void onError(AdError error);
}
