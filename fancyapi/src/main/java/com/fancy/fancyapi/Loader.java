package com.fancy.fancyapi;

import android.content.Context;
import android.util.Log;

import com.fancy.adsdk.lib.model.Core;

class Loader {
    static Core load(Context context) {
        Log.d("FancyAd", "Normal Loader");
        return new InnerTextCore();
    }
}
