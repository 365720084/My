package com.ptg.ptgapi;

import android.content.Context;
import android.util.Log;

import com.ptg.adsdk.lib.model.Core;

class Loader {
    static Core load(Context context) {
        Log.d("PtgAd", "Normal Loader");
        return new InnerTextCore();
    }
}
