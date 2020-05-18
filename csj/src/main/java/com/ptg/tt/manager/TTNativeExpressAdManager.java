package com.ptg.tt.manager;

import android.content.Context;

import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.ptg.adsdk.lib.constants.ProviderError;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.provider.PtgAdNative;
import com.ptg.adsdk.lib.utils.Logger;
import com.ptg.tt.adaptor.TTNativeExpressAdAdaptor;
import com.ptg.tt.config.TTAdManagerHolder;
import com.ptg.tt.utils.TTSlotBuilder;

import java.util.List;

public class TTNativeExpressAdManager {
    static final String TAG = "TTNativeExpressAdManager";

    private Context context;
    private TTAdNative mTTAdNative;

    public TTNativeExpressAdManager(Context context) {
        this.context = context;
        this.mTTAdNative = TTAdManagerHolder.get().createAdNative(context);
    }

    public void loadNativeExpressAd(final AdSlot adSlot, final PtgAdNative.NativeExpressAdListener listener) {
        if (!this.checkRequirement()) {
            listener.onError(ProviderError.SDK_NOT_READY, "Provider不可用");
            return;
        }

        com.bytedance.sdk.openadsdk.AdSlot ttSlot = TTSlotBuilder.Build(adSlot);

        mTTAdNative.loadNativeExpressAd(ttSlot, new TTAdNative.NativeExpressAdListener() {

            @Override
            public void onError(int i, String s) {
                listener.onError(i, s);
            }

            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> list) {
                if (list == null || list.isEmpty() || list.get(0) == null) {
                    listener.onError(ProviderError.SDK_NO_AD, "TTFeedsAdManager no ad");
                    return;
                }

                final TTNativeExpressAd ttNativeExpressAd = list.get(0);
                listener.onNativeExpressAdLoad(new TTNativeExpressAdAdaptor(ttNativeExpressAd));
            }
        });
    }

    private boolean checkRequirement() {
        if (this.context == null) {
            Logger.e( "context is null");
            return false;
        }

        if (this.mTTAdNative == null) {
            Logger.e( "sdk not init");
            return false;
        }

        return true;
    }
}
