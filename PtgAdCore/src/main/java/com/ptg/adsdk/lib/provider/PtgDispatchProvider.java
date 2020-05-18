package com.ptg.adsdk.lib.provider;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.constants.ProviderError;
import com.ptg.adsdk.lib.dispatcher.DispatchManager;
import com.ptg.adsdk.lib.dispatcher.policy.DispatchPolicyCandidate;
import com.ptg.adsdk.lib.model.AdSlot;

import java.util.HashMap;
import java.util.Map;

public class PtgDispatchProvider implements PtgAdNative {
    static final String TAG = "PtgDispatchProvider";
    static final PtgAdNative nullProvider = new PtgDispatcherProviderWrapper(null);

    private Map<String, PtgAdNative> provider;
    private DispatchManager dispatchManager;

    public PtgDispatchProvider() {
        this.provider = new HashMap<>();
    }

    public PtgDispatchProvider addProvider(PtgAdNative provider) {
        this.provider.put(provider.getName(), new PtgDispatcherProviderWrapper(provider));return this;
    }

    public PtgDispatchProvider setDispatchManager(DispatchManager dispatchManager) {
        this.dispatchManager = dispatchManager;
        return this;
    }

    public void init(Context context) {
        for (Map.Entry<String, PtgAdNative> entry : this.provider.entrySet()) {
            entry.getValue().init(context);
        }
    }

    @Override
    public String getName() {
        return "dispatcher";
    }

    @Override
    public void loadFeedAd(Context context, AdSlot slot, @NonNull FeedAdListener listener) {
        if(TextUtils.isEmpty(slot.getPtgSlotID())){
            listener.onError(ProviderError.SDK_NULL_COEDID, "ptgSlotId 为空, 请确认AdSlot参数");
            return;
        }

        getProvider(slot).loadFeedAd(context, slot, listener);
    }


    @Override
    public void loadSplashAd(Activity activity, AdSlot slot, @NonNull SplashAdListener listener) {
        if(TextUtils.isEmpty(slot.getPtgSlotID())){
            listener.onError(ProviderError.SDK_NULL_COEDID, "ptgSlotId 为空， 请确认AdSlot参数");
            return;
        }

        getProvider(slot).loadSplashAd(activity, slot, listener);
    }

    @Override
    public void loadNativeExpressAd(Context context, AdSlot adSlot, @NonNull NativeExpressAdListener listener) {
        if(TextUtils.isEmpty(adSlot.getPtgSlotID())){
            listener.onError(ProviderError.SDK_NULL_COEDID, "ptgSlotId 为空， 请确认AdSlot参数");
            return;
        }

        getProvider(adSlot).loadNativeExpressAd(context, adSlot, listener);
    }

    private PtgAdNative getProviderByDispatchPolicy(AdSlot slot, DispatchPolicyCandidate candidate) {
        if (candidate == null || candidate.isEmpty()) {
            Log.e(TAG, "no candidate");
            return nullProvider;
        }

        if (candidate.getCandidates().size() == 1) {
            slot.setDispatchPolicyItem(candidate.getCandidates().get(0));
            slot.setCodeID(candidate.getCandidates().get(0).getConsumerSlotId());
            return this.provider.get(candidate.getCandidates().get(0).getConsumerType());
        } else {
            Log.e(TAG, "not support async provider");
            return nullProvider;
        }
    }

    private PtgAdNative getProvider(AdSlot slot) {
        DispatchPolicyCandidate candidate = dispatchManager.dispatchPolicy(slot.getPtgSlotID());

        PtgAdNative provider = getProviderByDispatchPolicy(slot, candidate);
        if (provider == null) {
            return nullProvider;
        }

        Log.d(TAG, String.format("请求广告位: %s, 使用消耗方: %s", slot.getCodeId(), provider.getName()));

        return provider;
    }
}
