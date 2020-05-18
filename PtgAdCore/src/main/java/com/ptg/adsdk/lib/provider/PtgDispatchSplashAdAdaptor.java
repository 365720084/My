package com.ptg.adsdk.lib.provider;


import android.view.View;

import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgSplashAd;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.tracking.TrackingData;
import com.ptg.adsdk.lib.tracking.TrackingManager;

public class PtgDispatchSplashAdAdaptor implements PtgSplashAd {
    private AdSlot slot;
    private PtgSplashAd ad;
    private TrackingData trackingData;

    PtgDispatchSplashAdAdaptor(PtgSplashAd ad, AdSlot slot, TrackingData trackingData) {
        this.ad = ad;
        this.slot = slot;
        this.trackingData = trackingData;
    }

    @Override
    public int getInteractionType() {
        return ad.getInteractionType();
    }

    @Override
    public void setDownloadListener(PtgAppDownloadListener downloadListener) {
        ad.setDownloadListener(downloadListener);
    }

    @Override
    public void setSplashInteractionListener(final AdInteractionListener listener) {
        ad.setSplashInteractionListener(new AdInteractionListener() {
            @Override
            public void onAdClicked(View view, int type) {
                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getClickTracking(), trackingData);
                listener.onAdClicked(view, type);
            }

            @Override
            public void onAdShow(View view, int type) {
                if (!trackingData.isFirstImp()) {
                    return;
                }

                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getImpTracking(), trackingData);
                trackingData.setFirstImp(false);
                listener.onAdShow(view, type);
            }

            @Override
            public void onAdSkip() {
                listener.onAdSkip();
            }

            @Override
            public void onAdTimeOver() {
                listener.onAdTimeOver();
            }
        });
    }
}
