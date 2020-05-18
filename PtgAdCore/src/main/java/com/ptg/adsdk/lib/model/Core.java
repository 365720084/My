package com.ptg.adsdk.lib.model;

import android.app.Activity;
import android.content.Context;

public interface Core {

    void initialize(Context application);


    void getSplashAd(Activity activity,AdSlot adSlot, Callback<AdObject> callback);
    void getFeedAd(Context context,AdSlot adSlot, Callback<AdObject> callback);

    void onAdClicked(Ad ad);
}
