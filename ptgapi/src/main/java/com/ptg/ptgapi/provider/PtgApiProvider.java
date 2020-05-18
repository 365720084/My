package com.ptg.ptgapi.provider;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.ptg.ptgapi.PtgAdProxy;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.ptgapi.manager.PtgFeedAdManager;
import com.ptg.ptgapi.manager.PtgFeedExpressAdManager;
import com.ptg.ptgapi.manager.PtgSplashAdManager;

public class PtgApiProvider implements PtgAdNative {
    static final String providerName = "ptgapi";

    public PtgApiProvider() {
    }

    @Override
    public void init(Context context) {
        PtgAdProxy.initialize(context);

    }

    @Override
    public String getName() {
        return providerName;
    }

    @Override
    public void loadFeedAd(Context context, AdSlot slot, @NonNull FeedAdListener listener) {
        PtgFeedAdManager ptgFeedAdManager=new PtgFeedAdManager();
        ptgFeedAdManager.loadFeedAd(context,slot,listener);
    }

    @Override
    public void loadSplashAd(Activity activity, AdSlot slot, @NonNull SplashAdListener listener) {
        PtgSplashAdManager ptgSplashAdManager=new PtgSplashAdManager(activity);
        ptgSplashAdManager.loadSplashAd(slot,listener);
    }

    @Override
    public void loadNativeExpressAd(Context context, AdSlot adSlot, @NonNull NativeExpressAdListener listener) {
        PtgFeedExpressAdManager ptgFeedAdManager=new PtgFeedExpressAdManager();
        ptgFeedAdManager.loadNativeExpressAd(context,adSlot,listener);
    }
}
