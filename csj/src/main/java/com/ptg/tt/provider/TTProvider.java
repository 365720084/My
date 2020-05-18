package com.ptg.tt.provider;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.tt.config.TTAdManagerHolder;
import com.ptg.tt.manager.TTFeedsAdManager;
import com.ptg.tt.manager.TTNativeExpressAdManager;
import com.ptg.tt.manager.TTSplashAdManager;

public class TTProvider implements PtgAdNative {

    public TTProvider() {
    }

    public void init(Context context) {
        TTAdManagerHolder.init(context);
    }

    @Override
    public String getName() {
        return "tt";
    }

    @Override
    public void loadFeedAd(Context context, AdSlot slot, @NonNull FeedAdListener listener) {
        TTFeedsAdManager manager = new TTFeedsAdManager(context);
        manager.loadFeedAd(slot, listener);
    }


    @Override
    public void loadSplashAd(Activity activity, AdSlot slot, @NonNull SplashAdListener listener) {
        TTSplashAdManager manager = new TTSplashAdManager(activity);
        manager.loadSplashAd(slot, listener);
    }

    @Override
    public void loadNativeExpressAd(Context context, AdSlot adSlot, @NonNull NativeExpressAdListener listener) {
        TTNativeExpressAdManager manager = new TTNativeExpressAdManager(context);
        manager.loadNativeExpressAd(adSlot, listener);
    }
}
