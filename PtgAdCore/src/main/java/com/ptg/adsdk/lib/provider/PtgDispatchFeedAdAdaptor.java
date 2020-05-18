package com.ptg.adsdk.lib.provider;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ptg.adsdk.lib.interf.PtgAppDownloadListener;
import com.ptg.adsdk.lib.interf.PtgDownloadStatusController;
import com.ptg.adsdk.lib.interf.PtgFeedAd;
import com.ptg.adsdk.lib.interf.PtgNativeAd;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.model.PtgFilterWord;
import com.ptg.adsdk.lib.model.PtgImage;
import com.ptg.adsdk.lib.tracking.TrackingData;
import com.ptg.adsdk.lib.tracking.TrackingManager;

import java.util.List;
import java.util.Map;

public class PtgDispatchFeedAdAdaptor implements PtgFeedAd {
    private AdSlot slot;
    private PtgFeedAd ad;
    private TrackingData trackingData;

    PtgDispatchFeedAdAdaptor(PtgFeedAd ad, AdSlot slot, TrackingData trackingData) {
        this.ad = ad;
        this.slot = slot;
        this.trackingData = trackingData;
    }

    @Override
    public void setVideoAdListener(VideoAdListener listener) {
        ad.setVideoAdListener(listener);
    }

    @Override
    public double getVideoDuration() {
        return ad.getVideoDuration();
    }

    @Nullable
    @Override
    public PtgImage getVideoCoverImage() {
        return ad.getVideoCoverImage();
    }

    @Override
    public Bitmap getAdLogo() {
        return ad.getAdLogo();
    }

    @Override
    public String getTitle() {
        return ad.getTitle();
    }

    @Override
    public String getDescription() {
        return ad.getDescription();
    }

    @Override
    public String getButtonText() {
        return ad.getButtonText();
    }

    @Override
    public int getAppScore() {
        return ad.getAppScore();
    }

    @Override
    public int getAppCommentNum() {
        return ad.getAppCommentNum();
    }

    @Override
    public int getAppSize() {
        return ad.getAppSize();
    }

    @Override
    public String getSource() {
        return ad.getSource();
    }

    @Override
    public PtgImage getIcon() {
        return ad.getIcon();
    }

    @Override
    public List<PtgImage> getImageList() {
        return ad.getImageList();
    }

    @Override
    public int getInteractionType() {
        return ad.getInteractionType();
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
    public void registerViewForInteraction(@NonNull ViewGroup container, @NonNull View clickView, final AdInteractionListener listener) {
        ad.registerViewForInteraction(container, clickView, new AdInteractionListener() {
            @Override
            public void onAdClicked(View view, PtgNativeAd ad) {
                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getClickTracking(), trackingData);
                listener.onAdClicked(view, ad);
            }

            @Override
            public void onAdCreativeClick(View view, PtgNativeAd ad) {
                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getClickTracking(), trackingData);
                listener.onAdCreativeClick(view, ad);
            }

            @Override
            public void onAdShow(PtgNativeAd ad) {
                if (!trackingData.isFirstImp()) {
                    return;
                }

                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getImpTracking(), trackingData);
                trackingData.setFirstImp(false);
                listener.onAdShow(ad);
            }
        });
    }

    @Override
    public void registerViewForInteraction(@NonNull ViewGroup container, @NonNull List<View> clickViews, @Nullable List<View> creativeViews, final AdInteractionListener listener) {
        ad.registerViewForInteraction(container, clickViews, creativeViews, new AdInteractionListener() {

            @Override
            public void onAdClicked(View view, PtgNativeAd ad) {
                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getClickTracking(), trackingData);
                listener.onAdClicked(view, ad);
            }

            @Override
            public void onAdCreativeClick(View view, PtgNativeAd ad) {
                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getClickTracking(), trackingData);
                listener.onAdCreativeClick(view, ad);
            }

            @Override
            public void onAdShow(PtgNativeAd ad) {
                if (!trackingData.isFirstImp()) {
                    return;
                }

                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getImpTracking(), trackingData);
                trackingData.setFirstImp(false);
                listener.onAdShow(ad);
            }
        });
    }

    @Override
    public PtgDownloadStatusController getDownloadStatusController() {
        return ad.getDownloadStatusController();
    }

    @Override
    public void setDownloadListener(PtgAppDownloadListener listener) {
        ad.setDownloadListener(listener);
    }

    @Override
    public void setActivityForDownloadApp(@NonNull Activity activity) {
        ad.setActivityForDownloadApp(activity);
    }

    @Override
    public View getAdView() {
        return ad.getAdView();
    }

    @Override
    public Map<String, Object> getMediaExtraInfo() {
        return ad.getMediaExtraInfo();
    }

    @Override
    public void destroy() {
        ad.destroy();
    }

    @Override
    public void resume() {
        ad.resume();
    }
}
