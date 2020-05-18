package com.ptg.adsdk.lib.provider;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.constants.ProviderError;
import com.ptg.adsdk.lib.interf.PtgFeedAd;
import com.ptg.adsdk.lib.interf.PtgNativeExpressAd;
import com.ptg.adsdk.lib.interf.PtgSplashAd;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.tracking.TrackingData;
import com.ptg.adsdk.lib.tracking.TrackingManager;

public class PtgDispatcherProviderWrapper implements PtgAdNative {
    PtgAdNative provider;

    public PtgDispatcherProviderWrapper(PtgAdNative provider) {
        this.provider = provider;
    }

    @Override
    public void init(Context context) {
        if (this.provider == null) {
            return;
        }
        this.provider.init(context);
    }

    @Override
    public String getName() {
        if (this.provider == null) {
            return "null";
        }
        return provider.getName();
    }

    @Override
    public void loadFeedAd(Context context, final AdSlot slot, @NonNull final FeedAdListener listener) {
        if (this.provider == null) {
            listener.onError(ProviderError.SDK_NULL_PROVIDER, "可用SDK为空，请确认分发策略配置，以及是否引用该模块");
            return;
        }
        if (slot.getDispatchPolicyItem() == null) {
            listener.onError(ProviderError.SDK_INTERNAL, "内部错误，无分发策略");
            return;
        }

        String codeId = slot.getDispatchPolicyItem().getConsumerSlotId();
        slot.setCodeID(codeId);

        final TrackingData trackingData = new TrackingData(slot.getPtgSlotID(), codeId);
        trackingData.setAdKey(slot.getDispatchPolicyItem().getAdKey());

        TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getBidTracking(), trackingData);

        provider.loadFeedAd(context,slot, new FeedAdListener() {
            @Override
            public void onError(int code, String message) {
                trackingData.setErrorCode(code);
                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getErrorTracking(), trackingData);

                listener.onError(code,message);
            }

            @Override
            public void onFeedAdLoad(final PtgFeedAd ad) {
                PtgFeedAd resultAd = new PtgDispatchFeedAdAdaptor(ad, slot, trackingData);
                listener.onFeedAdLoad(resultAd);
            }
        });
    }

    @Override
    public void loadSplashAd(Activity activity, final AdSlot slot, @NonNull final SplashAdListener listener) {
        if (this.provider == null) {
            listener.onError(ProviderError.SDK_NULL_PROVIDER, "可用SDK为空，请确认分发策略配置，以及是否引用该模块");
            return;
        }
        if (slot.getDispatchPolicyItem() == null) {
            listener.onError(ProviderError.SDK_INTERNAL, "内部错误，无分发策略");
            return;
        }

        String codeId = slot.getDispatchPolicyItem().getConsumerSlotId();
        slot.setCodeID(codeId);

        final TrackingData trackingData = new TrackingData(slot.getPtgSlotID(), codeId);
        trackingData.setAdKey(slot.getDispatchPolicyItem().getAdKey());

        TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getBidTracking(), trackingData);

        provider.loadSplashAd(activity, slot, new SplashAdListener() {

            @Override
            public void onError(int code, String message) {
                trackingData.setErrorCode(code);
                TrackingManager.get().DoTracking(slot.getDispatchPolicyItem().getErrorTracking(), trackingData);

                listener.onError(code, message);
            }

            @Override
            public void onTimeout() {
                listener.onTimeout();
            }

            @Override
            public void onSplashAdLoad(final PtgSplashAd ad) {
                PtgSplashAd resultAd = new PtgDispatchSplashAdAdaptor(ad, slot, trackingData);

                resultAd.setSplashInteractionListener(new PtgSplashAd.AdInteractionListener() {
                    @Override
                    public void onAdClicked(View view, int type) {

                    }

                    @Override
                    public void onAdShow(View view, int type) {

                    }

                    @Override
                    public void onAdSkip() {

                    }

                    @Override
                    public void onAdTimeOver() {

                    }
                });

                listener.onSplashAdLoad(resultAd);
            }
        });
    }

    @Override
    public void loadNativeExpressAd(Context context, final AdSlot adSlot, final NativeExpressAdListener listener) {
        if (this.provider == null) {
            listener.onError(ProviderError.SDK_NULL_PROVIDER, "可用SDK为空，请确认分发策略配置，以及是否引用该模块");
            return;
        }
        if (adSlot.getDispatchPolicyItem() == null) {
            listener.onError(ProviderError.SDK_INTERNAL, "内部错误，无分发策略");
            return;
        }

        String codeId = adSlot.getDispatchPolicyItem().getConsumerSlotId();
        adSlot.setCodeID(codeId);

        final TrackingData trackingData = new TrackingData(adSlot.getPtgSlotID(), codeId);
        trackingData.setAdKey(adSlot.getDispatchPolicyItem().getAdKey());

        TrackingManager.get().DoTracking(adSlot.getDispatchPolicyItem().getBidTracking(), trackingData);

        provider.loadNativeExpressAd(context, adSlot, new NativeExpressAdListener() {

            @Override
            public void onError(int code, String message) {
                trackingData.setErrorCode(code);
                TrackingManager.get().DoTracking(adSlot.getDispatchPolicyItem().getErrorTracking(), trackingData);

                listener.onError(code, message);
            }

            @Override
            public void onNativeExpressAdLoad(final PtgNativeExpressAd ad) {
                PtgNativeExpressAd resultAd = new PtgDispatchNativeExpressAdAdaptor(ad, adSlot, trackingData);

                resultAd.setExpressInteractionListener(new PtgNativeExpressAd.AdInteractionListener() {
                    @Override
                    public void onAdDismiss() {

                    }

                    @Override
                    public void onAdClicked(View view, int type) {

                    }

                    @Override
                    public void onAdShow(View view, int type) {

                    }

                    @Override
                    public void onRenderFail(View view, String msg, int code) {

                    }

                    @Override
                    public void onRenderSuccess(View view, float width, float height) {

                    }
                });

                listener.onNativeExpressAdLoad(resultAd);
            }
        });
    }
}
