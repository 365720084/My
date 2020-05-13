package com.fancy.fancyapi.model;

import android.content.Context;

public interface Core {

    void initialize(Context application);


    void getSplashAd(AdSlot adSlot, Callback<AdObject> callback);
    void getFeedAd(AdSlot adSlot, Callback<AdObject> callback);

    void onAdClicked(Ad ad);
}
