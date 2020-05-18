package com.ptg.adsdk.lib.core;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;

import com.ptg.adsdk.lib.base.PtgSDKConfig;
import com.ptg.adsdk.lib.core.model.AppVersion;
import com.ptg.adsdk.lib.core.model.DeviceInfo;
import com.ptg.adsdk.lib.uniquecode.AppIdsUpdater;
import com.ptg.adsdk.lib.utils.Logger;

import org.json.JSONObject;

public class SdkConfig {

    public static String ip = "client";
    public static String mid = "";
    public static int si = 17029;//信息流广告id   开屏广告 17012
    public static String ua = "";
    public static String device_type = "";

    public static DeviceInfo device;
    public static String v = "1";
    public static String mimes = "icon,mp4,img";
    public static AppVersion app;
    //1.视频前贴2.视频暂停3.视频角标4.视频悬浮5.banner6.开屏7.插屏8.信息流
    public static int AD_SPLASH = 6;
    public static int AD_FEED = 8;
    public static int AD_BANNER = 5;

    public static JSONObject getDevice(Context context) {
        if (device == null) {
            return new JSONObject();
        }
        return device.getJson(context);
    }

    public static JSONObject getApp() {
        if (app == null) {
            return new JSONObject();
        }
        return app.getJson();
    }

    public static void initConfig(Context context, PtgSDKConfig config) {
        initConfig(context, config.getPtgAppId());
    }

    public static void initConfig(Context context, String mid) {
        SdkConfig.mid = mid;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                SdkConfig.ua = WebSettings.getDefaultUserAgent(context);
            } else {
                SdkConfig.ua = System.getProperty("http.agent");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (SdkConfig.ua == null) SdkConfig.ua = "";
        SdkConfig.device_type = String.valueOf(isTablet(context) ? 2 : 1);
        SdkConfig.device = DeviceInfo.generate(context, new AppIdsUpdater() {
            @Override
            public void OnIdsAvalid(@NonNull String ids) {
                Logger.e("OnIdsAvalid:"+ids);
                DeviceInfo info = SdkConfig.device;
                if(info!=null){
                    info.setOaid(ids);
                    SdkConfig.device = info;
                }

            }
        });
        SdkConfig.app = AppVersion.generate(context);
//        SdkConfig.ip = DeviceInfoUtil.getIpAddress(context);
    }

    private static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static DeviceInfo getDevice() {
        return device;
    }
}
