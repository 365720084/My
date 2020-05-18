package com.ptg.tt.manager;


import android.content.Context;
import android.util.Log;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.ptg.adsdk.lib.constants.ProviderError;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.tt.adaptor.TTSplashAdAdaptor;
import com.ptg.tt.config.TTAdManagerHolder;
import com.ptg.tt.utils.TTSlotBuilder;

public class TTSplashAdManager {
    static final String TAG = "TTSplashAdManager";
    //开屏广告加载超时时间,建议大于3000,这里为了冷启动第一次加载到广告并且展示,示例设置了3000ms
    private static final int AD_TIME_OUT = 3000;

    Context context;
    private TTAdNative mTTAdNative;

    public TTSplashAdManager(Context context) {
        this.context = context;
        this.mTTAdNative = TTAdManagerHolder.get().createAdNative(context);
    }

    public void loadSplashAd(final AdSlot adSlot, final PtgAdNative.SplashAdListener listener) {
        if (!this.checkRequirement()) {
            listener.onError(ProviderError.SDK_NOT_READY, "Provider不可用");
            return;
        }

        if (adSlot.getAdContainer() == null) {
            listener.onError(ProviderError.SDK_PARAM_ERR, "not container provide");
            return;
        }

        com.bytedance.sdk.openadsdk.AdSlot ttSlot = TTSlotBuilder.Build(adSlot);

        mTTAdNative.loadSplashAd(ttSlot, new TTAdNative.SplashAdListener() {
            @Override
            public void onError(int i, String s) {
                listener.onError(i, s);
            }

            @Override
            public void onTimeout() {
                listener.onTimeout();
            }

            @Override
            public void onSplashAdLoad(final TTSplashAd ttSplashAd) {
                adSlot.getAdContainer().removeAllViews();
                adSlot.getAdContainer().addView(ttSplashAd.getSplashView());

                if(!adSlot.needSkipView()){
                    ttSplashAd.setNotAllowSdkCountdown();
                }

                listener.onSplashAdLoad(new TTSplashAdAdaptor(ttSplashAd));
            }
        });
    }

    private boolean checkRequirement() {
        if (this.context == null) {
            Log.e(TAG, "context is null");
            return false;
        }

        if (this.mTTAdNative == null) {
            Log.e(TAG, "sdk not init");
            return false;
        }

        return true;
    }
}
