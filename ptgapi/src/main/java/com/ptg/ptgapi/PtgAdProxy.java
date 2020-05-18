package com.ptg.ptgapi;

import android.app.Activity;
import android.content.Context;

import com.ptg.adsdk.lib.model.Ad;
import com.ptg.adsdk.lib.model.AdObject;
import com.ptg.adsdk.lib.model.AdSlot;
import com.ptg.adsdk.lib.model.Callback;
import com.ptg.adsdk.lib.model.Core;

import java.util.List;

public class PtgAdProxy {
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


    public static void getSplashAd(Activity activity,AdSlot slot, Callback<AdObject> callback) {
        if (core != null) {
            core.getSplashAd(activity,slot,callback);
        }
    }

    public static void getFeedAd(Context context,AdSlot slot, Callback<AdObject> callback) {
        if (core != null) {
            core.getFeedAd(context,slot,callback);
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
