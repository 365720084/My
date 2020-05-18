package com.ptg.adsdk.lib.provider;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.interf.Error;
import com.ptg.adsdk.lib.interf.PtgFeedAd;
import com.ptg.adsdk.lib.interf.PtgNativeExpressAd;
import com.ptg.adsdk.lib.interf.PtgSplashAd;
import com.ptg.adsdk.lib.model.AdSlot;

public interface PtgAdNative {
    void init(Context context);

    String getName();

    void loadFeedAd(Context context, AdSlot slot, @NonNull FeedAdListener listener);

    void loadSplashAd(Activity activity,AdSlot slot, @NonNull SplashAdListener listener);

    void loadNativeExpressAd(Context context, AdSlot adSlot, @NonNull NativeExpressAdListener listener);

    interface SplashAdListener extends Error {
        @MainThread
        void onError(int code, String message);

        void onTimeout();

        @MainThread
        void onSplashAdLoad(PtgSplashAd ad);
    }

    interface FeedAdListener extends Error {
        @MainThread
        void onError(int code, String message);

        @MainThread
        void onFeedAdLoad(PtgFeedAd ad);
    }

    interface NativeExpressAdListener extends Error {
        @MainThread
        void onError(int code, String message);

        @MainThread
        void onNativeExpressAdLoad(PtgNativeExpressAd ad);
    }
}
