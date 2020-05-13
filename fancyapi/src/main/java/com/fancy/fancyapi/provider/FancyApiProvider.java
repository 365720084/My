package com.fancy.fancyapi.provider;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.fancy.fancyapi.FancyAdProxy;
import com.fancy.adsdk.lib.model.AdSlot;
import com.fancy.adsdk.lib.provider.FancyAdNative;
import com.fancy.fancyapi.manager.FancyFeedAdManager;
import com.fancy.fancyapi.manager.FancyFeedExpressAdManager;
import com.fancy.fancyapi.manager.FancySplashAdManager;

public class FancyApiProvider implements FancyAdNative {
    static final String providerName = "fancyapi";

    public FancyApiProvider() {
    }

    @Override
    public void init(Context context) {
        FancyAdProxy.initialize(context);

    }

    @Override
    public String getName() {
        return providerName;
    }

    @Override
    public void loadFeedAd(Context context, AdSlot slot, @NonNull FeedAdListener listener) {
        FancyFeedAdManager fancyFeedAdManager=new FancyFeedAdManager();
        fancyFeedAdManager.loadFeedAd(context,slot,listener);
    }

    @Override
    public void loadSplashAd(Activity activity, AdSlot slot, @NonNull SplashAdListener listener) {
        FancySplashAdManager fancySplashAdManager=new FancySplashAdManager(activity);
        fancySplashAdManager.loadSplashAd(slot,listener);
    }

    @Override
    public void loadNativeExpressAd(Context context, AdSlot adSlot, @NonNull NativeExpressAdListener listener) {
        FancyFeedExpressAdManager fancyFeedAdManager=new FancyFeedExpressAdManager();
        fancyFeedAdManager.loadNativeExpressAd(context,adSlot,listener);
    }
}
