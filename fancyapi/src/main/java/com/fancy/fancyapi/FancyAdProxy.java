package com.fancy.fancyapi;

import android.content.Context;

import com.fancy.adsdk.lib.model.Ad;
import com.fancy.adsdk.lib.model.AdObject;
import com.fancy.adsdk.lib.model.AdSlot;
import com.fancy.adsdk.lib.model.Callback;
import com.fancy.adsdk.lib.model.Core;

import java.util.List;

public class FancyAdProxy {
    private static Core core;

    /**
     * Sdk初始化
     *
     * @param application App application
     */
    public static void initialize(Context application) {
        if (application == null) {
            throw new IllegalArgumentException();
        }
        core = Loader.load(application);
        core.initialize(application);
    }


    public static void getSplashAd(AdSlot slot,  Callback<AdObject> callback) {
        if (core != null) {
            core.getSplashAd(slot,callback);
        }
    }

    public static void getFeedAd(AdSlot slot, Callback<AdObject> callback) {
        if (core != null) {
            core.getFeedAd(slot,callback);
        }
    }


    /**
     * 当广告被点击后调用
     *
     * @param adlist 广告
     */
    public static void onAdClicked(List<Ad> adlist) {
        if (adlist != null&&adlist.size()>0) {
            for(int i=0;i<adlist.size();i++){
                core.onAdClicked(adlist.get(i));
            }
        }
    }
    /**
     * 当广告被点击后调用
     *
     * @param ad 广告
     */
    public static void onAdClicked(Ad ad) {
        if (ad != null&&ad.getClk()!=null&&ad.getClk().size()>0) {
            core.onAdClicked(ad);
        }
    }
}
