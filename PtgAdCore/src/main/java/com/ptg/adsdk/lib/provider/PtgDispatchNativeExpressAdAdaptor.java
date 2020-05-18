package com.ptg.adsdk.lib.provider;

import android.app.Activity;
import android.view.View;

import com.ptg.adsdk.lib.component.PtgDislikeDialogAbstract;
import com.ptg.adsdk.lib.interf.PtgAdDislike;
import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgNativeExpressAd;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.adsdk.lib.tracking.TrackingData;
import com.ptg.adsdk.lib.tracking.TrackingManager;

import java.util.List;
import java.util.Map;

public class PtgDispatchNativeExpressAdAdaptor implements PtgNativeExpressAd {
    private AdSlot slot;
    private PtgNativeExpressAd ad;
    private TrackingData trackingData;

    PtgDispatchNativeExpressAdAdaptor(PtgNativeExpressAd ad, AdSlot slot, TrackingData trackingData) {
        this.ad = ad;
        this.slot = slot;
        this.trackingData = trackingData;
    }

    @Override
    public View getExpressAdView() {
        return ad.getExpressAdView();
    }

    @Override
    public int getImageMode() {
        return ad.getImageMode();
    }

    @Override
    public List<PtgFilterWord> getFilterWords() {
        return ad.getFilterWords();
    }

    @Override
    public void setExpressInteractionListener(final ExpressAdInteractionListener listener1) {
        ad.setExpressInteractionListener(new ExpressAdInteractionListener() {

            @Override
            public void onAdClicked(View view, int type) {
                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getClickTracking(), trackingData);
                listener1.onAdClicked(view, type);
            }

            @Override
            public void onAdShow(View view, int type) {
                if (!trackingData.isFirstImp()) {
                    return;
                }

                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getImpTracking(), trackingData);
                trackingData.setFirstImp(false);
                listener1.onAdShow(view, type);
            }

            @Override
            public void onRenderFail(View view, String msg, int code) {
                listener1.onRenderFail(view, msg, code);
            }

            @Override
            public void onRenderSuccess(View view, float width, float height) {
                listener1.onRenderSuccess(view, width, height);
            }
        });
    }

    @Override
    public void setExpressInteractionListener(final AdInteractionListener listener1) {
        ad.setExpressInteractionListener(new AdInteractionListener() {

            @Override
            public void onAdClicked(View view, int type) {
                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getClickTracking(), trackingData);
                listener1.onAdClicked(view, type);
            }

            @Override
            public void onAdShow(View view, int type) {
                if (!trackingData.isFirstImp()) {
                    return;
                }

                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getImpTracking(), trackingData);
                trackingData.setFirstImp(false);
                listener1.onAdShow(view, type);
            }

            @Override
            public void onRenderFail(View view, String msg, int code) {
                trackingData.setErrorCode(code);
                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getErrorTracking(), trackingData);

                listener1.onRenderFail(view, msg, code);
            }

            @Override
            public void onRenderSuccess(View view, float width, float height) {
                listener1.onRenderSuccess(view, width, height);
            }

            @Override
            public void onAdDismiss() {
                listener1.onAdDismiss();
            }
        });
    }

    @Override
    public void setDownloadListener(PtgAppDownloadListener listener1) {
        ad.setDownloadListener(listener1);
    }

    @Override
    public int getInteractionType() {
        return ad.getInteractionType();
    }

    @Override
    public void render() {
        ad.render();
    }

    @Override
    public void destroy() {
        ad.destroy();
    }

    @Override
    public void setDislikeCallback(Activity activity, PtgAdDislike.DislikeInteractionCallback callback) {
        ad.setDislikeCallback(activity, callback);
    }

    @Override
    public void setDislikeDialog(PtgDislikeDialogAbstract dislikeDialog) {
        ad.setDislikeDialog(dislikeDialog);
    }

    @Override
    public void showInteractionExpressAd(Activity activity) {
        ad.showInteractionExpressAd(activity);
    }

    @Override
    public void setSlideIntervalTime(int slideIntervalTime) {
        ad.setSlideIntervalTime(slideIntervalTime);
    }

    @Override
    public void setVideoAdListener(ExpressVideoAdListener listener1) {
        ad.setVideoAdListener(listener1);
    }

    @Override
    public void setCanInterruptVideoPlay(boolean canInterruptVideoPlay) {
        ad.setCanInterruptVideoPlay(canInterruptVideoPlay);
    }

    @Override
    public Map<String, Object> getMediaExtraInfo() {
        return ad.getMediaExtraInfo();
    }
}
