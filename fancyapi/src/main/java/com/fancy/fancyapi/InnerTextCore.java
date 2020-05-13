package com.fancy.fancyapi;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.fancy.adsdk.lib.core.SdkConfig;
import com.fancy.adsdk.lib.core.model.AdErrorImpl;
import com.fancy.adsdk.lib.core.net.NetUtils;
import com.fancy.adsdk.lib.model.Ad;
import com.fancy.adsdk.lib.model.AdError;
import com.fancy.adsdk.lib.model.AdObject;
import com.fancy.adsdk.lib.model.AdSlot;
import com.fancy.adsdk.lib.model.Callback;
import com.fancy.adsdk.lib.model.Core;
import com.fancy.adsdk.lib.model.Keywords;

import java.util.HashMap;
import java.util.Map;

public class InnerTextCore implements Core {

    private static final String TAG = InnerTextCore.class.getSimpleName();

    private Map<String, Keywords> keywordsMap = new HashMap<>();



    @Override
    public void initialize(Context application) {
//        SdkConfig.initConfig(application, 109);
        Log.d(TAG, "initialize 1435: " );
    }


    @Override
    public void getSplashAd(AdSlot adSlot, Callback<AdObject> callback) {
        if (!checkValid()) {
            callback.onError(new AdErrorImpl(AdError.ERROR_NO_INIT, "未初始化"));
            return;
        }
//        if (si <= 0) {
//            callback.onError(new AdErrorImpl(AdError.ERROR_PARAM, "si不能为空"));
//            return;
//        }
        InnerTextApi.get().getAd( SdkConfig.AD_SPLASH,adSlot,callback);
    }

    @Override
    public void getFeedAd(AdSlot adSlot, Callback<AdObject> callback) {
        if (!checkValid()) {
            callback.onError(new AdErrorImpl(AdError.ERROR_NO_INIT, "未传入mid"));
            return;
        }
        InnerTextApi.get().getAd( SdkConfig.AD_FEED,adSlot,callback);

    }


    @Override
    public void onAdClicked(Ad ad) {
        if (!checkValid()) {
            return;
        }
        if (ad == null || ad.getClk() == null || ad.getClk().size() == 0) return;
        NetUtils.asyncSimpleReport(ad.getClk());

    }

    private boolean checkValid() {
        return !TextUtils.isEmpty(SdkConfig.mid);
    }
}
