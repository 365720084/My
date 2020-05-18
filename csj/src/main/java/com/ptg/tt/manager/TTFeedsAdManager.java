package com.ptg.tt.manager;

import android.content.Context;
import android.util.Log;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTFeedAd;
import com.ptg.adsdk.lib.constants.ProviderError;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.tt.adaptor.TTFeedsAdAdaptor;
import com.ptg.tt.config.TTAdManagerHolder;
import com.ptg.tt.utils.TTSlotBuilder;

import java.util.List;

public class TTFeedsAdManager {
    static final String TAG = "TTFeedsAdManager";

    Context context;
    private TTAdNative mTTAdNative;

    public TTFeedsAdManager(Context context) {
        this.context = context;
        this.mTTAdNative = TTAdManagerHolder.get().createAdNative(context);
    }

    public void loadFeedAd(final AdSlot adSlot, final PtgAdNative.FeedAdListener listener) {
        if (!this.checkRequirement()) {
            listener.onError(ProviderError.SDK_NOT_READY, "Provider不可用");
            return;
        }

        com.bytedance.sdk.openadsdk.AdSlot ttSlot = TTSlotBuilder.Build(adSlot);

        mTTAdNative.loadFeedAd(ttSlot, new TTAdNative.FeedAdListener() {
            @Override
            public void onError(int i, String s) {
                Log.e(TAG, s);
                listener.onError(i, s);
            }

            @Override
            public void onFeedAdLoad(List<TTFeedAd> list) {
                if (list == null || list.isEmpty() || list.get(0) == null) {
                    listener.onError(ProviderError.SDK_NO_AD, "TTFeedsAdManager no ad");
                    return;
                }

                final TTFeedAd ttFeedAd = list.get(0);
                listener.onFeedAdLoad(new TTFeedsAdAdaptor(ttFeedAd));
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
