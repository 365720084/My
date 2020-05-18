package com.ptg.tt.config;

import android.content.Context;
import android.util.Log;

import com.bytedance.sdk.openadsdk.TTAdConfig;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdManager;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.ptg.adsdk.lib.PtgAdSdk;

public class TTAdManagerHolder {
    static final String TAG = "TTAdManagerHolder";

    private static boolean sInit;

    public static TTAdManager get() {
        if (!sInit) {
            Log.e(TAG, "TTAdSdk is not init, please check.");
            return TTAdSdk.getAdManager();
        }
        return TTAdSdk.getAdManager();
    }

    public static void init(Context context) {
        doInit(context);
    }

    //step1:接入网盟广告sdk的初始化操作，详情见接入文档和穿山甲平台说明
    private static void doInit(Context context) {
        if (!sInit) {
            TTAdSdk.init(context, buildConfig(context));
            sInit = true;
        }
    }

    private static TTAdConfig buildConfig(Context context) {
        return new TTAdConfig.Builder()
                .appId(PtgAdSdk.getConfig().getTtAppId())
                .appName(PtgAdSdk.getConfig().getAppName())
                .debug(PtgAdSdk.getConfig().isDebug())
                .build();
    }
}
